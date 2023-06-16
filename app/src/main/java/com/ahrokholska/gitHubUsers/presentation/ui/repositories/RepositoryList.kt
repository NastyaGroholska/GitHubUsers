package com.ahrokholska.gitHubUsers.presentation.ui.repositories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.ahrokholska.gitHubUsers.R
import com.ahrokholska.gitHubUsers.presentation.model.Repository
import com.ahrokholska.gitHubUsers.presentation.utils.ListOfLazyPagingItems

@Composable
fun RepositoryList(repositories: LazyPagingItems<Repository>, modifier: Modifier) {
    val itemMargin = dimensionResource(R.dimen.list_item_margin)
    val itemShape = RoundedCornerShape(dimensionResource(R.dimen.list_item_corner_radius))
    ListOfLazyPagingItems(
        screenKey = "RepositoryList",
        items = repositories,
        modifier = modifier
    ) { repository, index ->
        RepositoryItem(
            repository = repository, modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = itemMargin, end = itemMargin, top = itemMargin,
                    bottom = if (index == repositories.itemCount - 1) itemMargin else 0.dp
                )
                .clip(itemShape)
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.3f))
                .border(
                    width = dimensionResource(R.dimen.list_item_boarder_width),
                    color = MaterialTheme.colorScheme.outline,
                    shape = itemShape
                )
                .padding(dimensionResource(R.dimen.list_item_padding))
        )
    }
}