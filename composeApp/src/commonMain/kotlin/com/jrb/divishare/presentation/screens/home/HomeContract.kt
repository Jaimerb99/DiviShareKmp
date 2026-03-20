package com.jrb.divishare.presentation.screens.home

import com.jrb.divishare.domain.model.Group
import com.jrb.divishare.presentation.common.BaseContract

data class HomeUiState(
    val groups: List<Group> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) : BaseContract.UiState

sealed class HomeUiIntent : BaseContract.UiIntent {
    data object LoadGroups : HomeUiIntent()
    data class ClickGroup(val groupId: Int) : HomeUiIntent()
    data object ClickAddProject : HomeUiIntent()
    data object ClickProfile : HomeUiIntent()
}

sealed class HomeUiEffect : BaseContract.UiEffect {
    data class NavigateToProject(val groupId: Int) : HomeUiEffect()
    data object NavigateToNewProject : HomeUiEffect()
    data object NavigateToProfile : HomeUiEffect()
    data object NavigateToLogin : HomeUiEffect()
}