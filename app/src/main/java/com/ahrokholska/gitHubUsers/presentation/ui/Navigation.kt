package com.ahrokholska.gitHubUsers.presentation.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahrokholska.gitHubUsers.presentation.ui.repositories.RepositoryScreen
import com.ahrokholska.gitHubUsers.presentation.ui.users.UserScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users") {
        composable(route = "users") {
            UserScreen(hiltViewModel()) { login, picture ->
                val encodedUrl = URLEncoder.encode(picture, StandardCharsets.UTF_8.toString())
                navController.navigate("user_repository/$login/$encodedUrl")
            }
        }
        composable(route = "user_repository/{userName}/{userPictureURL}") {
            RepositoryScreen(
                viewModel = hiltViewModel(),
                userName = it.arguments?.getString("userName").orEmpty(),
                pictureURL = it.arguments?.getString("userPictureURL").orEmpty()
            ) {
                navController.navigateUp()
            }
        }
    }
}