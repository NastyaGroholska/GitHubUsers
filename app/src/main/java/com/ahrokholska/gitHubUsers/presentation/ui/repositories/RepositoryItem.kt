package com.ahrokholska.gitHubUsers.presentation.ui.repositories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.ahrokholska.gitHubUsers.R
import com.ahrokholska.gitHubUsers.presentation.model.Repository

@Composable
fun RepositoryItem(repository: Repository, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(dimensionResource(R.dimen.repository_item_hole_size))
                .background(MaterialTheme.colorScheme.inversePrimary, shape = CircleShape)
                .border(
                    width = dimensionResource(R.dimen.list_item_boarder_width),
                    color = MaterialTheme.colorScheme.outline,
                    shape = CircleShape
                )
        )
        Text(
            text = repository.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.repository_item_elements_offset)))
        Text(
            text = repository.description.orEmpty(),
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.repository_item_elements_offset)))
        Text(
            text = repository.createdAt,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.End)
        )
    }
}