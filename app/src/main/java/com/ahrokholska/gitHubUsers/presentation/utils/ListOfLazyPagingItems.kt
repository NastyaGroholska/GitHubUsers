package com.ahrokholska.gitHubUsers.presentation.utils

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any> ListOfLazyPagingItems(
    screenKey: String,
    items: LazyPagingItems<T>,
    modifier: Modifier,
    item: @Composable (T, Int) -> Unit
) {
    val context = LocalContext.current.applicationContext
    LaunchedEffect(key1 = items.loadState) {
        if (items.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                (items.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = modifier) {
        if (items.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            val state = rememberForeverLazyListState(key = screenKey)

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                state = state
            ) {
                items(items.itemCount) { index ->
                    items[index]?.let {
                        item(it, index)
                    }
                }
                item {
                    if (items.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}