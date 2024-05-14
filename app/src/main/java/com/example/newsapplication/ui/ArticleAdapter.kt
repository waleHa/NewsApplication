package com.example.newsapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.databinding.ItemArticleBinding
import com.example.newsapplication.domain.model.Article

class ArticleAdapter: ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val list = getItem(position)
        holder.bind(list)
//        holder.itemView.setOnClickListener {
//            onPeopleItemClicked(list)
//        }
    }

    class ArticleViewHolder(private val binding:ItemArticleBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:Article){
            Glide.with(binding.imageViewArticle.context)
                .load(item.urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.imageViewArticle)
            binding.textViewTitle.text = item.title
            binding.textViewDescription.text = item.description
        }
    }

    companion object {
        val DiffCallBack = object :DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem

        }
    }
}