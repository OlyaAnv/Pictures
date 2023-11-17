package com.example.pictures.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pictures.data.models.Photo
import com.example.pictures.databinding.PictureItemBinding

class PictureAdapter(private val listener: Listener) :
    RecyclerView.Adapter<PictureAdapter.PictureHolder>() {

    var list = emptyList<Photo>()

    class PictureHolder(val binding: PictureItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        val binding = PictureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        Glide.with(holder.binding.imageView.context)
            .load(list[position].url)
            .centerCrop()
            .into(holder.binding.imageView)
        holder.binding.imageView.contentDescription = list[position].description
        holder.binding.imageView.setOnClickListener {
            listener.onClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    interface Listener {
        fun onClick(photo: Photo)
    }
}