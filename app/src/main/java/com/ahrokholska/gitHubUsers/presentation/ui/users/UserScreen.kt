package com.ahrokholska.gitHubUsers.presentation.ui.users

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun UserScreen(viewModel: UserViewModel, onNavigateToUserRepositories: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val users = viewModel.users.collectAsLazyPagingItems()
        Text(text = "Refresh all",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.inversePrimary)
                .clickable {
                    users.refresh()
                }
        )
        UsersList(
            users = users,
            modifier = Modifier.fillMaxSize(),
            onItemClick = onNavigateToUserRepositories
        )
    }
}