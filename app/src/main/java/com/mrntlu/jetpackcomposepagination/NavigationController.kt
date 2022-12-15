package com.mrntlu.jetpackcomposepagination

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mrntlu.jetpackcomposepagination.ui.ManuelPagingListScreen
import com.mrntlu.jetpackcomposepagination.ui.PagingListScreen

@Composable
fun NavigationComposable(
    paddingValues: PaddingValues,
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier
            .padding(paddingValues),
        navController = navController,
        startDestination = "paging"
    ) {
        composable("paging") {
            PagingListScreen()
        }

        composable("manuel_paging") {
            ManuelPagingListScreen()
        }
    }
}