package com.ahrokholska.gitHubUsers.presentation.ui.repositories

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.ahrokholska.gitHubUsers.R
import com.ahrokholska.gitHubUsers.presentation.utils.ActionBar
import com.ahrokholska.gitHubUsers.presentation.utils.BackButton
import com.ahrokholska.gitHubUsers.presentation.utils.RefreshButton

@Composable
fun RepositoryScreen(
    viewModel: RepositoryViewModel,
    userName: String,
    pictureURL: String,
    onBackClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    Column(modifier = Modifier.fillMaxSize()) {
        val repositories = viewModel.repositories.collectAsLazyPagingItems()
        ActionBar(
            screenName = "Repositories",
            leftItem = @Composable { BackButton(onclick = onBackClick) },
            rightItem = @Composable { RefreshButton(onclick = repositories::refresh) }
        )

        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                Row {
                    UserInfo(
                        userName = userName, pictureURL = pictureURL,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(2f)
                    )
                    RepositoryList(
                        repositories = repositories,
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxHeight()
                    )
                }
            }

            else -> {
                UserInfo(userName = userName, pictureURL = pictureURL, modifier = Modifier)
                RepositoryList(
                    repositories = repositories,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun UserInfo(userName: String, pictureURL: String, modifier: Modifier) {
    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = pictureURL,
            contentDescription = userName,
            modifier = Modifier
                .size(dimensionResource(R.dimen.detailed_user_picture_size))
                .padding(dimensionResource(R.dimen.detailed_user_picture_padding))
                .clip(CircleShape),
            error = painterResource(id = R.drawable.user_svgrepo_com)
        )
        Text(
            text = userName,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.repository_item_elements_offset))
        )
    }
}