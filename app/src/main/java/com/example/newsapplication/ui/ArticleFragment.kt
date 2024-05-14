package com.example.newsapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.newsapplication.databinding.FragmentArticleBinding
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.util.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {
    lateinit var binding: FragmentArticleBinding

    val viewmodel: ArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArticleAdapter{
            navigationToDetails(it)
        }
        viewmodel.newsList.observe(viewLifecycleOwner) {
            adapter.submitList(it.body()?.articles)
        }
    }

    fun navigationToDetails(article: Article){
        val bundle = Bundle().also {
            it.putParcelable(Constant.ARTICLE_KEY,article)
        }
    }
}