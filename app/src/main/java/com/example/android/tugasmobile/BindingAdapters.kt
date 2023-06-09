package com.example.android.tugasmobile

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.tugasmobile.network.Article
import com.example.android.tugasmobile.overview.ItemGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Log.d("cek bindImage1", "passsed")
    imgUrl?.let {
        Log.d("cek bindImage2", "passsed")
        imgView.load(imgUrl)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Article>?) {
    val adapter = recyclerView.adapter as ItemGridAdapter

    adapter.submitList(data)
    Log.d("cek2", "${adapter.itemCount}")
}