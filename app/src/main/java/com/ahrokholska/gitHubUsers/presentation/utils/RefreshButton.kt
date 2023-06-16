package com.ahrokholska.gitHubUsers.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ahrokholska.gitHubUsers.R

@Composable
fun RefreshButton(onclick: () -> Unit) {
    ButtonWithIconAndTextNoAnimation(
        text = "Refresh",
        iconId = R.drawable.baseline_refresh_24,
        isIconFirst = false,
        onClick = onclick,
        modifier = Modifier
    )
}