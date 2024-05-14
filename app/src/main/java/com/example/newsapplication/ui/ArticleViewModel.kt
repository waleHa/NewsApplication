package com.example.newsapplication.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.data.repository.Repository
import com.example.newsapplication.domain.model.Article
import com.example.newsapplication.domain.model.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository : Repository
) : ViewModel() {

    private val _newsList = MutableLiveData<Response<NewsModel>>()
    val newsList : LiveData<Response<NewsModel>> = _newsList

    init {
        getList ()
    }

    private fun getList () {
        val TAG = "TAG: ArticleViewmodel"
        viewModelScope.launch {
            try {
                val result = repository.getNews()
                if (result.isSuccessful()){
                    _newsList.postValue(result)
                }
            } catch (e:Exception){
                Log.e(TAG,e.localizedMessage)
            }
        }
    }
}