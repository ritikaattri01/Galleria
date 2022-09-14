package com.app.imagespagination.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.imagespagination.R
import com.app.imagespagination.data.local.ImageEntity
import com.app.imagespagination.databinding.SingleItemImageBinding
import com.bumptech.glide.Glide

class ImagesAdapter(private var onImageClicked: (ImageEntity?) -> Unit) :
    RecyclerView.Adapter<ImagesAdapter.MainViewHolder>() {

    private val list: MutableList<ImageEntity> = mutableListOf()

    inner class MainViewHolder(
        val binding: SingleItemImageBinding,
    ) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            SingleItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = list.getOrNull(holder.absoluteAdapterPosition)
        holder.binding.apply {
            Glide.with(root.context).load(item?.downloadUrl).placeholder(R.drawable.placeholder)
                .into(imageView)
        }
        holder.binding.imageView.setOnClickListener {
            onImageClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addItems(items: List<ImageEntity>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}