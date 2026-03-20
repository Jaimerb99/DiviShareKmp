package com.jrb.divishare.presentation.screens.forgotpassword

import com.jrb.divishare.presentation.common.BaseContract

data class ForgotPasswordUiState(
    val email: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isButtonEnabled: Boolean = false
) : BaseContract.UiState

sealed class ForgotPasswordUiIntent : BaseContract.UiIntent {
    data class EmailChanged(val email: String) : ForgotPasswordUiIntent()
    data object SubmitRecovery : ForgotPasswordUiIntent()
}

sealed class ForgotPasswordUiEffect : BaseContract.UiEffect {
    data class ShowError(val message: String) : ForgotPasswordUiEffect()
    data object NavigateBack : ForgotPasswordUiEffect()
}