package com.example.android.tugasmobile

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.android.tugasmobile.network.Article
import com.example.android.tugasmobile.network.MediaMetaData
import com.example.android.tugasmobile.overview.ItemGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, img: MediaMetaData?) {
    Log.d("cek bindImage1", "passsed")
    img?.let {
        Log.d("cek bindImage2", "passsed")
//        imgView.load(img.ImageUrl)
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
    Log.d("cek2", "${adapter.itemCount}")
}