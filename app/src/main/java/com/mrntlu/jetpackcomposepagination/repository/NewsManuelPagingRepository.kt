package com.mrntlu.jetpackcomposepagination.repository

import com.mrntlu.jetpackcomposepagination.models.NewsResponse
import com.mrntlu.jetpackcomposepagination.service.NewsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsManuelPagingRepository @Inject constructor(
    private val newsApiService: NewsApiService
) {
    suspend fun getNews(page: Int): Flow<NewsResponse> = flow {
       try {
           emit(newsApiService.getNews(page))
       } catch (error: Exception) {
           emit(NewsResponse(emptyList(), error.message ?: "", 0))
       }
    }.flowOn(Dispatchers.IO)
}