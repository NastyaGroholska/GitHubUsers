package com.ahrokholska.gitHubUsers.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.ahrokholska.gitHubUsers.R

@Composable
fun ActionBar(
    screenName: String,
    leftItem: @Composable () -> Unit,
    rightItem: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(dimensionResource(id = R.dimen.button_padding))
        ) {
            leftItem()
        }
        Text(
            text = screenName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.align(Alignment.Center)
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(dimensionResource(id = R.dimen.button_padding))
        ) {
            rightItem()
        }
    }
}