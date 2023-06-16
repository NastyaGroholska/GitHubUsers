package com.ahrokholska.gitHubUsers.presentation.ui.users

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.ahrokholska.gitHubUsers.R
import com.ahrokholska.gitHubUsers.presentation.model.User

@Composable
fun UserItem(user: User, modifier: Modifier) {
    Row(
        modifier = modifier.padding(dimensionResource(R.dimen.list_item_padding)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.pictureURL,
            contentDescription = user.login,
            modifier = Modifier
                .size(dimensionResource(R.dimen.user_picture_size))
                .clip(CircleShape)
                .border(
                    width = dimensionResource(R.dimen.user_picture_boarder_width),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape
                ),
            error = painterResource(id = R.drawable.user_svgrepo_com)
        )
        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.user_picture_offset)))
        Text(
            text = user.login,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = GenericShape { size, _ ->
                        val rect = Rect(Offset.Zero, Size(size.width, size.height))
                        addArc(
                            oval = rect,
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 90f
                        )
                        lineTo(0f, size.height)
                        lineTo(0f, size.height / 2)
                        addArc(
                            oval = rect,
                            startAngleDegrees = 180f,
                            sweepAngleDegrees = 90f
                        )
                        lineTo(size.width, 0f)
                        lineTo(size.width, size.height / 2)
                    })
        )
    }
}