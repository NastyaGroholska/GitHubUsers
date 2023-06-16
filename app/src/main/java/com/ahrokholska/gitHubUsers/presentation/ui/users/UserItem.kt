package com.ahrokholska.gitHubUsers.presentation.ui.users

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.ahrokholska.gitHubUsers.R
import com.ahrokholska.gitHubUsers.presentation.model.User

@Composable
fun UserItem(user: User, modifier: Modifier) {
    Row(
        modifier = modifier.padding(dimensionResource(R.dimen.user_item_padding)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.pictureURL,
            contentDescription = user.login,
            modifier = Modifier
                .size(dimensionResource(R.dimen.user_picture_size))
                .clip(CircleShape),
            error = painterResource(id = R.drawable.user_svgrepo_com)
        )
        Spacer(modifier = Modifier.size(dimensionResource(R.dimen.user_picture_offset)))
        Text(
            text = user.login,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth()
        )
    }
}