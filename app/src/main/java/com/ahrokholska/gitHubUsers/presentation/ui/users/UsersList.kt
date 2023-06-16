package com.ahrokholska.gitHubUsers.presentation.ui.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.ahrokholska.gitHubUsers.R
import com.ahrokholska.gitHubUsers.presentation.model.User
import com.ahrokholska.gitHubUsers.presentation.utils.ListOfLazyPagingItems

@Composable
fun UsersList(
    users: LazyPagingItems<User>,
    modifier: Modifier,
    onItemClick: (String, String) -> Unit
) {
    val itemMargin = dimensionResource(R.dimen.list_item_margin)
    ListOfLazyPagingItems(
        screenKey = "UsersList",
        items = users,
        modifier = modifier
    ) { user, index ->
        UserItem(
            user = user, modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = itemMargin, end = itemMargin, top = itemMargin,
                    bottom = if (index == users.itemCount - 1) itemMargin else 0.dp
                )
                .clickable {
                    onItemClick(user.login, user.pictureURL)
                }
        )
        Divider(color = MaterialTheme.colorScheme.inverseOnSurface)
    }
}