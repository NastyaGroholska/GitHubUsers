package com.ahrokholska.gitHubUsers.presentation.ui.repositories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahrokholska.gitHubUsers.R

@Composable
fun RepositoryScreen(viewModel: RepositoryViewModel, userName: String, onBackClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        val repositories = viewModel.repositories.collectAsLazyPagingItems()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val interactionSource = remember { MutableInteractionSource() }
            Row(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onBackClick
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Back"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Back")
            }
            Row(
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { repositories.refresh() }
                    )
            ) {
                Text(text = "Refresh")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_refresh_24),
                    contentDescription = "Refresh"
                )
            }
        }
        Text(
            text = "Repositories of $userName",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inversePrimary)
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        )

        RepositoryList(
            repositories = repositories,
            modifier = Modifier.fillMaxSize()
        )
    }
}