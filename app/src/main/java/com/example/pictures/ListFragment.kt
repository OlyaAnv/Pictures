package com.example.pictures

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pictures.data.TestPicture
import com.example.pictures.viewmodel.PicturesViewModel

class ListFragment : Fragment(R.layout.list_fragment), PictureAdapter.Listener {

    private lateinit var viewModel: PicturesViewModel

    private lateinit var adapter: PictureAdapter
    private lateinit var rv: RecyclerView
    private lateinit var tvStatus: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PicturesViewModel::class.java]
        initViews(view)
        setupRecyclerView()
        observeViewModel()
    }



    override fun onClick(testPicture: TestPicture) {
        parentFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                DetailFragment.newInstance(testPicture.imageRes),
                " DetailFragment"
            )
            .addToBackStack(null)
            .commit()
    }

    private fun initViews(view: View) {
        rv = view.findViewById(R.id.rv)
        tvStatus = view.findViewById(R.id.tvStatus)
    }

    private fun setupRecyclerView(){
        rv.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = PictureAdapter(this)
        rv.adapter = adapter
    }


    private fun observeViewModel() {
        viewModel.testPhoto.observe(viewLifecycleOwner){
                list ->
            adapter.list = list
            adapter.notifyDataSetChanged()
        }
//        viewModel.status.observe(viewLifecycleOwner) { status ->
//            tvStatus.text = status
//        }
//        viewModel.photo.observe(viewLifecycleOwner){
//                list ->
//            adapter.mylist = list
//            adapter.notifyDataSetChanged()
//        }
    }

    private fun setupSwipeListener(rvShopList: RecyclerView) {
//        val callback = object : ItemTouchHelper.SimpleCallback(
//            0,
//            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//        ) {
//
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val item = adapter.currentList[viewHolder.adapterPosition]
//                viewModel.deleteShopItem(item)
//            }
//        }
//        val itemTouchHelper = ItemTouchHelper(callback)
//        itemTouchHelper.attachToRecyclerView(rvShopList)
    }
}