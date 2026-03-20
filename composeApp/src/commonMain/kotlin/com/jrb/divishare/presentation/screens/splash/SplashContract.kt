package com.jrb.divishare.presentation.screens.splash

import com.jrb.divishare.presentation.common.BaseContract

data class SplashUiState(
    val isCheckingDestination: Boolean = true
) : BaseContract.UiState

sealed class SplashUiIntent : BaseContract.UiIntent {
    data object CheckDestination : SplashUiIntent()
}

sealed class SplashUiEffect : BaseContract.UiEffect {
    data object NavigateToLogin : SplashUiEffect()
    data object NavigateToHome : SplashUiEffect()
}