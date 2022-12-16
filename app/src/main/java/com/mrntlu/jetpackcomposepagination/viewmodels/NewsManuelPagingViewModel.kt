package com.mrntlu.jetpackcomposepagination.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrntlu.jetpackcomposepagination.models.Article
import com.mrntlu.jetpackcomposepagination.repository.NewsManuelPagingRepository
import com.mrntlu.jetpackcomposepagination.utils.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsManuelPagingViewModel @Inject constructor(
    private val repository: NewsManuelPagingRepository,
): ViewModel() {
    val newsList = mutableStateListOf<Article>()

    private var page by mutableStateOf(1)
    var canPaginate by mutableStateOf(false)
    var listState by mutableStateOf(ListState.IDLE)

    init {
        getNews()
    }

    fun getNews() = viewModelScope.launch {
        if (page == 1 || (page != 1 && canPaginate) && listState == ListState.IDLE) {
            listState = if (page == 1) ListState.LOADING else ListState.PAGINATING

            repository.getNews(page).collect() {
                if (it.status == "ok") {
                    canPaginate = it.articles.size == 20

                    if (page == 1) {
                        newsList.clear()
                        newsList.addAll(it.articles)
                    } else {
                        newsList.addAll(it.articles)
                    }

                    listState = ListState.IDLE

                    if (canPaginate)
                        page++
                } else {
                    listState = if (page == 1) ListState.ERROR else ListState.PAGINATION_EXHAUST
                }
            }
        }
    }

    override fun onCleared() {
        page = 1
        listState = ListState.IDLE
        canPaginate = false
        super.onCleared()
    }
}