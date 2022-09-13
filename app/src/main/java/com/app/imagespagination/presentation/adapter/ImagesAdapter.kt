package com.app.imagespagination.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.imagespagination.data.local.ImageEntity
import com.app.imagespagination.databinding.SingleItemImageBinding

class ImagesAdapter : PagingDataAdapter<ImageEntity, ImagesAdapter.MainViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ImageEntity>() {
            override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean =
                oldItem == newItem
        }
    }

    inner class MainViewHolder(val binding: SingleItemImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            SingleItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            textView.text = item?.author
        }
    }
}