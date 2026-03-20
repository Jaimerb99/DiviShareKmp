package com.jrb.divishare.presentation.screens.forgotpassword

import androidx.lifecycle.viewModelScope
import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.usecase.auth.SendRecoveryEmailUseCase
import com.jrb.divishare.presentation.common.BaseViewModel
import com.jrb.divishare.utils.isValidEmail
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val sendRecoveryEmailUseCase: SendRecoveryEmailUseCase
) : BaseViewModel<ForgotPasswordUiState, ForgotPasswordUiIntent, ForgotPasswordUiEffect>() {

    override val initialState = ForgotPasswordUiState()

    override fun reduce(intent: ForgotPasswordUiIntent) {
        when (intent) {
            is ForgotPasswordUiIntent.EmailChanged -> {
                updateState {
                    copy(
                        email = intent.email,
                        isButtonEnabled = isValidEmail(intent.email)
                    )
                }
            }
            is ForgotPasswordUiIntent.SubmitRecovery -> sendEmail()
        }
    }

    private fun sendEmail() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            val result = sendRecoveryEmailUseCase(currentState.email)
            when (result) {
                is DiviResult.Success -> updateState { copy(isSuccess = true, isLoading = false) }
                is DiviResult.Error -> {
                    updateState { copy(isLoading = false) }
                    sendEffect(ForgotPasswordUiEffect.ShowError("No se pudo enviar el correo"))
                }
            }
        }
    }
}