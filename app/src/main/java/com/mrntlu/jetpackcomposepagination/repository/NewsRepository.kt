package com.mrntlu.jetpackcomposepagination.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mrntlu.jetpackcomposepagination.models.NewsPagingSource
import com.mrntlu.jetpackcomposepagination.service.NewsApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(
     private val newsApiService: NewsApiService
) {
    fun getNews() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            NewsPagingSource(newsApiService)
        }
    ).flow
}