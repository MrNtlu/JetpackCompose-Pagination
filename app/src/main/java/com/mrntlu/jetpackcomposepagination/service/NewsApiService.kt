package com.mrntlu.jetpackcomposepagination.service

import com.mrntlu.jetpackcomposepagination.models.NewsResponse
import com.mrntlu.jetpackcomposepagination.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything?q=apple&sortBy=popularity&apiKey=${Constants.API_KEY}&pageSize=20")
    suspend fun getNews(
        @Query("page") page: Int
    ): NewsResponse
}