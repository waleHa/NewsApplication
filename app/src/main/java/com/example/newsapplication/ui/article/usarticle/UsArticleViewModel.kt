package com.example.newsapplication.ui.article.usarticle

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
class UsArticleViewModel @Inject constructor(
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
        val TAG = "TAG: UsArticleViewModel"
        viewModelScope.launch {
            try {

                val result = repository.getUsNews()
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