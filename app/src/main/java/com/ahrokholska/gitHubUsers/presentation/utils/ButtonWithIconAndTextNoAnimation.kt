package com.ahrokholska.gitHubUsers.presentation.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.ahrokholska.gitHubUsers.R

@Composable
fun ButtonWithIconAndTextNoAnimation(
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