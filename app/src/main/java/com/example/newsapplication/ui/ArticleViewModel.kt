package com.example.newsapplication.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.data.repository.Repository
import com.example.newsapplication.domain.model.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository : Repository
) : ViewModel() {

    private val _newsListSuccess = MutableLiveData<Response<NewsModel>>()
    val newsListSuccess : LiveData<Response<NewsModel>> = _newsListSuccess

    private val _newsListLoading = MutableLiveData<Boolean>(true)
     val newsListLoading : LiveData<Boolean> = _newsListLoading

    init {
        getList ()
    }

    private fun getList () {
        val TAG = "TAG: ArticleViewmodel"
        viewModelScope.launch {
            try {

                val result = repository.getNews()
                if (result.isSuccessful()){
                    _newsListSuccess.postValue(result)
                }
            } catch (e:Exception){
                Log.e(TAG,e.localizedMessage)
            }finally {
                _newsListLoading.postValue(false)
            }
        }
    }
}