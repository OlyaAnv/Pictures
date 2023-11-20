package com.example.pictures.ui.scenes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pictures.R
import com.example.pictures.data.models.Photo
import com.example.pictures.ui.PictureAdapter
import com.example.pictures.ui.viewmodel.PicturesViewModel

class ListFragment : Fragment(R.layout.list_fragment), PictureAdapter.Listener {

    private lateinit var viewModel: PicturesViewModel

    private lateinit var adapter: PictureAdapter
    private lateinit var rv: RecyclerView
    private lateinit var tvStatus: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("mylog", "onCreate List")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PicturesViewModel::class.java]
        initViews(view)
        setupRecyclerView()
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
            tvStatus.text = status
            tvStatus.visibility = VISIBLE
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) progressBar.visibility = VISIBLE
            else progressBar.visibility = GONE
        }
    }
}