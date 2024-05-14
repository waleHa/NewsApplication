package com.example.newsapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentDetailsBinding
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.util.Constant.ARTICLE_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding : FragmentDetailsBinding? = null

    private val binding get() = _binding!!
    val TAG = "TAG: DetailedPeopleFragment"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val articleDTO = it.getParcelable(ARTICLE_KEY, Article::class.java)
            if (articleDTO != null) {
                updateUI(articleDTO)
            } else {
                showError()
            }
        }
    }
    private fun updateUI(article: Article) {
        with(binding) {
            Glide.with(binding.avatarImageView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.baseline_account_circle_24).into(avatarImageView)
            titleTextView.text = article.title
            descriptionTextView.text = article.description
        }
    }
    private fun showError() {
        // Implement error handling logic here
        Toast.makeText(context, "Failed to load article details.", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    }
