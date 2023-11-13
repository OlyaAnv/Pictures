package com.example.pictures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pictures.data.Photo
import com.example.pictures.data.TestPicture
import com.example.pictures.databinding.PictureItemBinding

class PictureAdapter(private val listener: Listener) :
    RecyclerView.Adapter<PictureAdapter.PictureHolder>() {

    var list = emptyList<TestPicture>()

    class PictureHolder(val binding: PictureItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureHolder {
        val binding = PictureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureHolder, position: Int) {
        holder.binding.imageView.setImageResource(list[position].imageRes)
        holder.binding.imageView.setOnClickListener {
            listener.onClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    interface Listener {
        fun onClick(testPicture: TestPicture)
    }
}