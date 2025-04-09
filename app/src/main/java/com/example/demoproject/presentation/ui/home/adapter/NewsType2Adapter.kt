package com.example.demoproject.presentation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.demoproject.databinding.NewsCardView2Binding
import com.example.demoproject.databinding.NewsCardViewBinding
import com.example.demoproject.domain.model.news.Article

class NewsType2Adapter: ListAdapter<Article, NewsType2Adapter.News2Holder>(MyDiffCallback()) {

    class News2Holder(private val binding: NewsCardView2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.txtTitle.text = item.title
            binding.txtAuthor.text = item.author
            binding.txtdetail.text = item.description
            binding.txtPublic.text = item.publishedAt
            binding.imageView2.load(item.urlToImage)
        }
    }

    class MyDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean = oldItem.title == newItem.title
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): News2Holder {
        val binding = NewsCardView2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return News2Holder(binding)
    }

    override fun onBindViewHolder(holder: News2Holder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}