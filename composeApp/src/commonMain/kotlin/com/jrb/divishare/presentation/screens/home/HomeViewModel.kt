package com.jrb.divishare.presentation.screens.home

import androidx.lifecycle.viewModelScope
import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.usecase.auth.GetUserIdUseCase
import com.jrb.divishare.domain.usecase.groups.GetMyGroupsUseCase
import com.jrb.divishare.presentation.common.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMyGroupsUseCase: GetMyGroupsUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : BaseViewModel<HomeUiState, HomeUiIntent, HomeUiEffect>() {

    override val initialState = HomeUiState()

    init {
        sendIntent(HomeUiIntent.LoadGroups)
    }

    override fun reduce(intent: HomeUiIntent) {
        when (intent) {
            is HomeUiIntent.LoadGroups -> fetchGroups()
            is HomeUiIntent.ClickGroup -> sendEffect(HomeUiEffect.NavigateToProject(intent.groupId))
            is HomeUiIntent.ClickAddProject -> sendEffect(HomeUiEffect.NavigateToNewProject)
            is HomeUiIntent.ClickProfile -> sendEffect(HomeUiEffect.NavigateToProfile)
        }
    }

    private fun fetchGroups() {
        viewModelScope.launch {
            updateState { copy(isLoading = true, error = null) }
            val userId = getUserIdUseCase()

            if (userId != null) {
                when (val result = getMyGroupsUseCase(userId)) {
                    is DiviResult.Success -> {
                        updateState { copy(groups = result.data, isLoading = false) }
                    }
                    is DiviResult.Error -> {
                        updateState { copy(isLoading = false, error = result.error.toString()) }
                    }
                }
            } else {
                updateState { copy(isLoading = false) }
                sendEffect(HomeUiEffect.NavigateToLogin)
            }
        }
    }
}