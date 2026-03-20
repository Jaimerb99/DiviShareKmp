package com.jrb.divishare.presentation.screens.registration

import androidx.lifecycle.viewModelScope
import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.repo.UserRepository
import com.jrb.divishare.presentation.common.BaseViewModel
import com.jrb.divishare.utils.isValidEmail
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val userRepository: UserRepository
) : BaseViewModel<RegistrationUiState, RegistrationUiIntent, RegistrationUiEffect>() {

    override val initialState = RegistrationUiState()

    override fun reduce(intent: RegistrationUiIntent) {
        when (intent) {
            is RegistrationUiIntent.NameChanged -> updateState { copy(name = intent.name).validate() }
            is RegistrationUiIntent.EmailChanged -> updateState { copy(email = intent.email).validate() }
            is RegistrationUiIntent.PhonePrefixChanged -> updateState { copy(phonePrefix = intent.prefix) }
            is RegistrationUiIntent.PhoneNumberChanged -> updateState { copy(phoneNumber = intent.number) }
            is RegistrationUiIntent.ClickDateBox -> updateState { copy(isDatePickerVisible = true) }
            is RegistrationUiIntent.DateSelected -> updateState { copy(birthDate = intent.date, isDatePickerVisible = false) }
            is RegistrationUiIntent.DismissDatePicker -> updateState { copy(isDatePickerVisible = false) }
            is RegistrationUiIntent.PasswordChanged -> updateState { copy(password = intent.password).validate() }
            is RegistrationUiIntent.ConfirmPasswordChanged -> updateState { copy(confirmPassword = intent.password).validate() }
            is RegistrationUiIntent.SubmitRegistration -> performRegistration()
        }
    }

    private fun RegistrationUiState.validate(): RegistrationUiState {
        val isValid = name.isNotEmpty() &&
                isValidEmail(email) &&
                password.isNotEmpty() &&
                password == confirmPassword
        return copy(isRegisterButtonEnabled = isValid)
    }

    private fun performRegistration() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }

            val result = userRepository.signUpWithEmail(
                email = currentState.email,
                password = currentState.password,
                fullName = currentState.name
            )

            when (result) {
                is DiviResult.Success -> sendEffect(RegistrationUiEffect.NavigateToHome)
                is DiviResult.Error -> sendEffect(RegistrationUiEffect.ShowError("Registration failed"))
            }

            updateState { copy(isLoading = false) }
        }
    }
}