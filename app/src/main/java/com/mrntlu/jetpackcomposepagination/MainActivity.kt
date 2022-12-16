package com.mrntlu.jetpackcomposepagination

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mrntlu.jetpackcomposepagination.ui.theme.JetpackComposePaginationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePaginationTheme {
                navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(navController)
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    navController: NavHostController,
) {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    selected = selectedItem == 0,
                    onClick = {
                        selectedItem = 0
                        navController.navigate("paging")
                    },
                    label = {
                        Text(text = "Paging 3")
                    },
                    icon = {
                        Icon(imageVector = Icons.Rounded.Home, contentDescription = "")
                    }
                )

                //TODO
                BottomNavigationItem(
                    selected = selectedItem == 1,
                    onClick = {
                        selectedItem = 1
                        navController.navigate("manuel_paging")
                    },
                    label = {
                        Text(text = "Manuel Paging")
                    },
                    icon = {
                        Icon(imageVector = Icons.Rounded.List, contentDescription = "")
                    }
                )
            }
        },
        content = { paddingValues ->
            NavigationComposable(
                paddingValues = paddingValues,
                navController = navController
            )
        }
    )
}