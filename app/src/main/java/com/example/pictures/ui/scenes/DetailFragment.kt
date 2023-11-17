package com.example.pictures.ui.scenes

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.example.pictures.R

private const val IMG_RES = "img_res_url"

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private lateinit var img: SubsamplingScaleImageView
    private var imgUrl: String? = null
    private var emptyImageRes = R.drawable.ic_baseline_broken_image_24

    companion object {
        fun newInstance(imgUrl: String): Fragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(IMG_RES, imgUrl)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
        Log.d("mylog", "onCreate Detail")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setPicture()
    }

    private fun parseParams() {
        imgUrl = arguments?.getString(IMG_RES)
    }

    private fun initViews(view: View) {
        img = view.findViewById(R.id.imageView)
    }

    private fun setPicture() {
        if (imgUrl == null) {
            img.setImage(ImageSource.resource(emptyImageRes))
        } else {
            Glide.with(this)
                .asBitmap()
                .load(imgUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        img.setImage(ImageSource.bitmap(resource))
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // Called when the resource is cleared.
                    }
                })
        }
    }
}