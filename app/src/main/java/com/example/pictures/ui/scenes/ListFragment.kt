package com.example.pictures.ui.scenes

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.internet_module.Photo
import com.example.pictures.R
import com.example.pictures.ui.PictureAdapter
import com.example.pictures.ui.viewmodel.PicturesViewModel
import timber.log.Timber

class ListFragment : Fragment(R.layout.list_fragment), PictureAdapter.Listener {

    private lateinit var viewModel: PicturesViewModel

    private lateinit var adapter: PictureAdapter
    private lateinit var rv: RecyclerView
    private lateinit var tvStatus: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("mylog").d("onCreate List")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PicturesViewModel::class.java]
        initViews(view)
        setupRecyclerView()
        setRefreshListener()
        observeViewModel()
    }

    override fun onClick(photo: Photo) {
        parentFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                DetailFragment.newInstance(photo.url),
                " DetailFragment"
            )
            .addToBackStack(null)
            .commit()
    }

    private fun initViews(view: View) {
        rv = view.findViewById(R.id.rv)
        tvStatus = view.findViewById(R.id.tvStatus)
        progressBar = view.findViewById(R.id.progressBar)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
    }

    private fun setupRecyclerView() {
        rv.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = PictureAdapter(this)
        rv.adapter = adapter
    }


    private fun observeViewModel() {
        viewModel.photo.observe(viewLifecycleOwner) { list ->
            adapter.list = list
            adapter.notifyDataSetChanged()
        }
        viewModel.status.observe(viewLifecycleOwner) { status ->
            if (status.isEmpty()) {
                tvStatus.visibility = GONE
            } else {
                tvStatus.text = status
                tvStatus.visibility = VISIBLE
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) progressBar.visibility = VISIBLE
            else progressBar.visibility = GONE
        }
    }

    private fun setRefreshListener() {
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.purple_500
            )
        )
        swipeRefreshLayout.setColorSchemeColors(Color.WHITE)
        swipeRefreshLayout.setOnRefreshListener {
            refreshData()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun refreshData() {
        if (isNetworkAvailable(requireContext())) {
            viewModel.clearListOfPhoto()
            viewModel.getDataFromInternet(dontUseCash = true)
        }
        //Toast.makeText(requireContext(), "Data Refreshed", Toast.LENGTH_SHORT).show()
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities!= null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}