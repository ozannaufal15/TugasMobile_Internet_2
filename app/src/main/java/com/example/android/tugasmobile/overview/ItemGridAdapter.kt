package com.example.android.tugasmobile.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.tugasmobile.databinding.ItemViewBinding
import com.example.android.tugasmobile.network.Article

class ItemGridAdapter: ListAdapter<Article,
        ItemGridAdapter.ArticleViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemViewBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    class ArticleViewHolder(private var binding:
                              ItemViewBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            //return oldItem.id==newItem.id
            return false
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            //return oldItem.equals(newItem)
            return false
        }
    }
}