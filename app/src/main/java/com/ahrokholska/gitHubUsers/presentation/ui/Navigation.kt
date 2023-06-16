package com.ahrokholska.gitHubUsers.presentation.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahrokholska.gitHubUsers.presentation.ui.repositories.RepositoryScreen
import com.ahrokholska.gitHubUsers.presentation.ui.users.UserScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users") {
        composable(route = "users") {
            UserScreen(hiltViewModel()) {
                navController.navigate("user_repository/$it")
            }
        }
        composable(route = "user_repository/{userName}") {
            RepositoryScreen(hiltViewModel(), it.arguments?.getString("userName").orEmpty()) {
                navController.navigateUp()
            }
        }
    }
}