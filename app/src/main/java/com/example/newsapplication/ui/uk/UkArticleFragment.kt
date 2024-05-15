package com.example.newsapplication.ui.uk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.databinding.FragmentUkArticleBinding
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.ui.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UkArticleFragment : Fragment() {
    lateinit var binding: FragmentUkArticleBinding

    val viewModel: UkArticleViewModel by viewModels()
    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUkArticleBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = ArticleAdapter { navigationToDetails(it) }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.newsListSuccess.observe(viewLifecycleOwner) {
            it.body()?.articles?.let { articles ->
                adapter.submitList(articles)
            }
        }

        viewModel.newsListLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun navigationToDetails(article: Article) {
//        val bundle = Bundle().apply {
//            putParcelable(Constant.ARTICLE_KEY, article)
//        }
//        findNavController().navigate(R.id.action_articleFragment_to_detailsFragment, bundle)
    }
}