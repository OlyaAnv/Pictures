package com.example.pictures

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.example.pictures.viewmodel.PicturesViewModel

private const val IMG_RES = "img_res_int"

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private lateinit var viewModel: PicturesViewModel

    private lateinit var img:SubsamplingScaleImageView
    private var imgRes = R.drawable.ic_baseline_broken_image_24

    companion object {
        fun newInstance(imgRes: Int): Fragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(IMG_RES, imgRes)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PicturesViewModel::class.java]
        initViews(view)
    }

    private fun parseParams(){
        imgRes = arguments?.getInt(IMG_RES) ?: R.drawable.ic_baseline_broken_image_24
    }

    private fun observeViewModel(){

    }

    private fun initViews(view: View){
        img = view.findViewById(R.id.imageView)
        img.setImage(ImageSource.resource(imgRes))
    }
}