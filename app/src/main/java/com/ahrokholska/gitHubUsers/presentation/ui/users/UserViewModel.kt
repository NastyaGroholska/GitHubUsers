package com.ahrokholska.gitHubUsers.presentation.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.ahrokholska.gitHubUsers.data.local.users.UserEntity
import com.ahrokholska.gitHubUsers.domain.mappers.toUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(pager: Pager<Int, UserEntity>) : ViewModel() {
    val users = pager.flow.map { pagingData ->
        pagingData.map { entity ->
            entity.toUser()
        }
    }.cachedIn(viewModelScope)
}