package com.example.android.tugasmobile.overview

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.android.tugasmobile.network.API_KEY
import com.example.android.tugasmobile.network.Article
import com.example.android.tugasmobile.network.NyTimesApi
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
enum class ApiStatus{LOADING, ERROR, DONE}

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()

    // The internal MutableLiveData that stores the status of the most recent request
    private val _articles = MutableLiveData<List<Article>>()

    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus> = _status

    val articles: LiveData<List<Article>> = _articles
    init {
        getPopularArticles()
    }
    /**
     * Gets Article from API
     * [Article] [List] [LiveData].
     */
    private fun getPopularArticles() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _articles.value = NyTimesApi.retrofitService.getPopularArticles(API_KEY).result
                _status.value = ApiStatus.DONE
            } catch(e: Exception) {
                _status.value = ApiStatus.ERROR
                _articles.value = listOf()
            }
        }
    }

    private suspend fun refresh(): Boolean{
        try {
            _articles.value = NyTimesApi.retrofitService.getPopularArticles(API_KEY).result
            _status.value = ApiStatus.DONE
        } catch(e: Exception) {
            _status.value = ApiStatus.ERROR
            _articles.value = listOf()
            return false
        }
        return false
    }
    fun onRefresh(view: SwipeRefreshLayout){
        viewModelScope.launch {
            view.isRefreshing = refresh()
        }
    }
}
