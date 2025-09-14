package com.example.techburg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.techburg.databinding.FragmentImageBinding

class ImageAdapter(
    private var items: MutableList<Int>
) : RecyclerView.Adapter<ImageViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(
            binding = FragmentImageBinding.inflate(layoutInflater,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun filterList(filteredList: ArrayList<Int>){
        items = filteredList
        notifyDataSetChanged()
    }
}

class ImageViewHolder(
    private val binding: FragmentImageBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Int) {
        binding.run {
            image.setImageResource(item)
        }
    }
}