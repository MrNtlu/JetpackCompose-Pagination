package com.mrntlu.jetpackcomposepagination.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mrntlu.jetpackcomposepagination.viewmodels.NewsViewModel

@Composable
fun PagingListScreen() {
    val viewModel = hiltViewModel<NewsViewModel>()

    val articles = viewModel.getBreakingNews().collectAsLazyPagingItems()

    LazyColumn {
        items(
            items = articles,
            key = { index -> index.url }
        ) { article ->
            Text(
                modifier = Modifier
                    .height(75.dp),
                text = article?.title ?: "",
            )

            Divider()
        }

        when (val state = articles.loadState.prepend) {
            is LoadState.Loading -> {
                Log.d("Test", "Prepend Loading")
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Gray),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Prepend Loading")

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }
            is LoadState.Error -> {
                Log.d("Test", "Prepend Error")
            }
            else -> {}
        }

        when (val state = articles.loadState.refresh) { //FIRST LOAD
            is LoadState.Error -> {
                Log.d("Test", "Refresh Loading")
            }
            is LoadState.Loading -> {
                Log.d("Test", "Refresh Loading")
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Refresh Loading")

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }
            else -> {}
        }

        when (val state = articles.loadState.append) { // Pagination
            is LoadState.Error -> {
                Log.d("Test", "Append Loading")
            }
            is LoadState.Loading -> {
                Log.d("Test", "Append Loading")
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Append Loading", color = Color.White)

                        CircularProgressIndicator(color = Color.White)
                    }
                }
            }
            else -> {}
        }
    }
}