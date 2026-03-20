package com.jrb.divishare.presentation.screens.onboarding

import com.jrb.divishare.presentation.common.BaseContract
import org.jetbrains.compose.resources.DrawableResource

data class OnboardingPage(
    val title: String,
    val description: String,
    val image: DrawableResource
)

data class OnboardingUiState(
    val currentPage: Int = 0,
    val pages: List<OnboardingPage> = emptyList()
) : BaseContract.UiState

sealed class OnboardingUiIntent : BaseContract.UiIntent {
    data object LoadPages : OnboardingUiIntent()
    data object ClickNext : OnboardingUiIntent()
    data object ClickSkip : OnboardingUiIntent()
}

sealed class OnboardingUiEffect : BaseContract.UiEffect {
    data object NavigateToLogin : OnboardingUiEffect()
}