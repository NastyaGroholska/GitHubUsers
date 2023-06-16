package com.ahrokholska.gitHubUsers.presentation.ui.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahrokholska.gitHubUsers.presentation.utils.ActionBar
import com.ahrokholska.gitHubUsers.presentation.utils.RefreshButton

@Composable
fun UserScreen(viewModel: UserViewModel, onNavigateToUserRepositories: (String, String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val users = viewModel.users.collectAsLazyPagingItems()
        ActionBar(
            screenName = "Users",
            leftItem = @Composable { Spacer(Modifier) },
            rightItem = @Composable { RefreshButton(onclick = users::refresh) }
        )
        UsersList(
            users = users,
            modifier = Modifier.fillMaxSize(),
            onItemClick = onNavigateToUserRepositories
        )
    }
}