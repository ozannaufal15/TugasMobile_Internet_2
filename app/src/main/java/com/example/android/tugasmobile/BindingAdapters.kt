package com.example.android.tugasmobile

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.tugasmobile.network.Article
import com.example.android.tugasmobile.network.Media
import com.example.android.tugasmobile.overview.ApiStatus
import com.example.android.tugasmobile.overview.ItemGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, img: List<Media>) {
    if(!img.isNullOrEmpty() && !img.get(0).mediaMetaData.isNullOrEmpty()){
        Glide.with(imgView)
            .load(img.get(0).mediaMetaData.last().ImageUrl).placeholder(R.drawable.blank_image).error(R.drawable.no_image)
            .into(imgView)
    }else{
        Glide.with(imgView).load("").error(R.drawable.no_image).into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Article>?) {
    val adapter = recyclerView.adapter as ItemGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("onClickItem")
fun onClickItem(view: View, article: Article){
    view.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.articleUrl))
        view.context.startActivity(intent)
    }
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

@BindingAdapter("creditText")
fun bindCreditText(textView: TextView, media: List<Media>){
    if(!media.isNullOrEmpty()){
        textView.text = media.get(0).copyright
    }
}