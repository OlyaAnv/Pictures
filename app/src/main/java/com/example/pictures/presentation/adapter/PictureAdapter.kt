package com.example.pictures.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pictures.R
import com.example.pictures.databinding.PictureItemBinding
import com.example.pictures.presentation.model.PhotoUI


class PictureAdapter(private val listener: Listener) :
    RecyclerView.Adapter<PictureAdapter.PictureHolder>() {

    var list = emptyList<PhotoUI>()

    class PictureHolder(val binding: PictureItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        val binding = PictureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        Glide.with(holder.binding.imageView.context)
            .load(list[position].url)
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
//            .skipMemoryCache(true)
            .centerCrop()
            .placeholder(R.drawable.blank_image)
            .into(holder.binding.imageView)
        holder.binding.imageView.contentDescription = list[position].title
        holder.binding.imageView.setOnClickListener {
            listener.onClick(list[position])
        }
        //holder.binding.textView.text = list[position].title
    }

    override fun getItemCount(): Int = list.size

    interface Listener {
        fun onClick(photo: PhotoUI)
    }
}