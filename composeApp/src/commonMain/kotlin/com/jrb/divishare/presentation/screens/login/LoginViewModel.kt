package com.jrb.divishare.presentation.screens.login

import androidx.lifecycle.viewModelScope
import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.usecase.auth.CheckOnboardingStatusUseCase
import com.jrb.divishare.domain.usecase.auth.LoginUseCase
import com.jrb.divishare.presentation.common.BaseViewModel
import com.jrb.divishare.utils.isValidEmail
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val checkOnboardingStatusUseCase: CheckOnboardingStatusUseCase
) : BaseViewModel<LoginUiState, LoginUiIntent, LoginUiEffect>() {

    override val initialState = LoginUiState()

    override fun reduce(intent: LoginUiIntent) {
        when (intent) {
            is LoginUiIntent.CheckOnboardingStatus -> checkOnboarding()
            is LoginUiIntent.EmailChanged -> updateEmail(intent.email)
            is LoginUiIntent.PasswordChanged -> updatePassword(intent.password)
            is LoginUiIntent.SubmitLogin -> performLogin()
            is LoginUiIntent.ClickForgotPassword -> sendEffect(LoginUiEffect.NavigateToForgotPassword)
            is LoginUiIntent.ClickSignUp -> sendEffect(LoginUiEffect.NavigateToRegister)
        }
    }

    private fun checkOnboarding() {
        viewModelScope.launch {
            val hasSeenOnboarding = checkOnboardingStatusUseCase()
            if (!hasSeenOnboarding) {
                sendEffect(LoginUiEffect.NavigateToOnboarding)
            } else {
                updateState { copy(isCheckingOnboarding = false) }
            }
        }
    }

    private fun updateEmail(newEmail: String) {
        updateState {
            copy(
                email = newEmail,
                isLoginButtonEnabled = newEmail.isNotEmpty() && isValidEmail(newEmail) && password.isNotEmpty()
            )
        }
    }

    private fun updatePassword(newPassword: String) {
        updateState {
            copy(
                password = newPassword,
                isLoginButtonEnabled = email.isNotEmpty() && isValidEmail(email) && newPassword.isNotEmpty()
            )
        }
    }

    private fun performLogin() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }

            val result = loginUseCase(
                email = currentState.email,
                password = currentState.password
            )

            when (result) {
                is DiviResult.Success -> {
                    sendEffect(LoginUiEffect.NavigateToHome)
                }
                is DiviResult.Error -> {
                    sendEffect(LoginUiEffect.ShowError("Invalid email or password"))
                }
            }

            updateState { copy(isLoading = false) }
        }
    }
}