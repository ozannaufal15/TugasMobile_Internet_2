package com.example.android.tugasmobile

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.tugasmobile.network.Article
import com.example.android.tugasmobile.network.MediaMetaData
import com.example.android.tugasmobile.overview.ApiStatus
import com.example.android.tugasmobile.overview.ItemGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, img: MediaMetaData?) {
    img?.let {
        Glide.with(imgView)
            .load(img.ImageUrl).placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Article>?) {
    val adapter = recyclerView.adapter as ItemGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView,
        status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        null -> {

        }
    }
}