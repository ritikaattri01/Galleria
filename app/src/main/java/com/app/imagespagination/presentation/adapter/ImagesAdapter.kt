package com.app.imagespagination.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.imagespagination.R
import com.app.imagespagination.data.local.ImageEntity
import com.app.imagespagination.databinding.SingleItemImageBinding
import com.bumptech.glide.Glide

class ImagesAdapter constructor(val onImageClicked: (ImageEntity?) -> Unit) :
    PagingDataAdapter<ImageEntity, ImagesAdapter.MainViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ImageEntity>() {
            override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean =
                oldItem.id == newItem.id
        }
    }

    inner class MainViewHolder(
        val binding: SingleItemImageBinding,
        private val onImageClicked: ((Int) -> Unit)?,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.imageView.setOnClickListener {
                onImageClicked?.invoke(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            SingleItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onImageClicked = { onImageClicked(getItem(it)) }
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            Glide.with(root.context).load(item?.downloadUrl).placeholder(R.drawable.placeholder).into(imageView)
        }
    }
}