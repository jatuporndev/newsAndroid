package com.example.demoproject.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.demoproject.databinding.NewsCardViewBinding
import com.example.demoproject.domain.model.news.Article

class NewsAdapter: ListAdapter<Article, NewsAdapter.NewsHolder>(MyDiffCallback()) {

    class NewsHolder(private val binding: NewsCardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.txtTitle.text = item.title
            binding.txtAuthor.text = item.author
            binding.imageView.load(item.urlToImage)


        }
    }

    class MyDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean = oldItem.title == newItem.title
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = NewsCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}