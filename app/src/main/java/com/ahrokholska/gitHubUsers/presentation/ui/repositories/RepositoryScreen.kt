package com.ahrokholska.gitHubUsers.presentation.ui.repositories

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.ahrokholska.gitHubUsers.R

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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            ButtonWithIconAnTextNoAnimation(
                text = "Back",
                iconId = R.drawable.baseline_arrow_back_24,
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(dimensionResource(id = R.dimen.button_padding))
            )
            Text(
                text = "Repositories",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.align(Alignment.Center)
            )
            ButtonWithIconAnTextNoAnimation(
                text = "Refresh",
                iconId = R.drawable.baseline_refresh_24,
                isIconFirst = false,
                onClick = repositories::refresh,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(dimensionResource(id = R.dimen.button_padding))
            )
        }

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
private fun ButtonWithIconAnTextNoAnimation(
    modifier: Modifier,
    text: String,
    @DrawableRes iconId: Int,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    isIconFirst: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val listOfItems = mutableListOf<@Composable () -> Unit>(
        @Composable { Text(text = text, color = contentColor) },
        @Composable { Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.button_icon_offset))) },
        @Composable {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = text,
                tint = contentColor
            )
        }
    )
    if (isIconFirst) {
        listOfItems.reverse()
    }
    Row(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
    ) {
        listOfItems.forEach { it() }
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