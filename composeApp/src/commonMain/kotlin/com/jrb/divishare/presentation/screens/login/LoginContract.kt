package com.jrb.divishare.presentation.screens.login

import com.jrb.divishare.presentation.common.BaseContract

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoginButtonEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val isCheckingOnboarding: Boolean = true
) : BaseContract.UiState

sealed class LoginUiIntent : BaseContract.UiIntent {
    data object CheckOnboardingStatus : LoginUiIntent()
    data class EmailChanged(val email: String) : LoginUiIntent()
    data class PasswordChanged(val password: String) : LoginUiIntent()
    data object SubmitLogin : LoginUiIntent()
    data object ClickForgotPassword : LoginUiIntent()
    data object ClickSignUp : LoginUiIntent()
}

sealed class LoginUiEffect : BaseContract.UiEffect {
    data object NavigateToOnboarding : LoginUiEffect()
    data object NavigateToHome : LoginUiEffect()
    data object NavigateToForgotPassword : LoginUiEffect()
    data object NavigateToRegister : LoginUiEffect()
    data class ShowError(val message: String) : LoginUiEffect()
}