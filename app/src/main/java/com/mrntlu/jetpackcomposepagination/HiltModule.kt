package com.mrntlu.jetpackcomposepagination

import com.mrntlu.jetpackcomposepagination.repository.NewsRepository
import com.mrntlu.jetpackcomposepagination.service.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {
    @Provides
    fun provideNewsRepository(newsApiService: NewsApiService): NewsRepository = NewsRepository(newsApiService)
}