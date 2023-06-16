package com.ahrokholska.gitHubUsers.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ahrokholska.gitHubUsers.R

@Composable
fun BackButton(onclick: () -> Unit) {
    ButtonWithIconAndTextNoAnimation(
        text = "Back",
        iconId = R.drawable.baseline_arrow_back_24,
        onClick = onclick,
        modifier = Modifier
    )
}