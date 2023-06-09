/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.tugasmobile.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.tugasmobile.network.API_KEY
import com.example.android.tugasmobile.network.Article
import com.example.android.tugasmobile.network.NyTimesApi
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    private val _articles = MutableLiveData<List<Article>>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    val articles: LiveData<List<Article>> = _articles
    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        Log.d("cek init viewmodel", "passsed")
        getPopularArticles()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getPopularArticles() {
        Log.d("cek getArticl1", "passsed")
        viewModelScope.launch {
            try {
                Log.d("cek before request", "passsed")
                _articles.value = NyTimesApi.retrofitService.getPopularArticles(API_KEY).result
                Log.d("cek after request", "passsed")
                _status.value = "listResult"
            } catch(e: Exception) {
                _status.value = "Failure: ${e.message}"
                Log.d("cek req exception", "passsed " +
                        "${e.message}")
            }
        }
    }
}
