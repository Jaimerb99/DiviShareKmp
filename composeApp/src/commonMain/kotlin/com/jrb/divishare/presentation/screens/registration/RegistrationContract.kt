package com.jrb.divishare.presentation.screens.registration

import com.jrb.divishare.presentation.common.BaseContract

data class RegistrationUiState(
    val name: String = "",
    val email: String = "",
    val phonePrefix: String = "+34",
    val phoneNumber: String = "",
    val birthDate: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isDatePickerVisible: Boolean = false,
    val isRegisterButtonEnabled: Boolean = false,
    val isLoading: Boolean = false
) : BaseContract.UiState

sealed class RegistrationUiIntent : BaseContract.UiIntent {
    data class NameChanged(val name: String) : RegistrationUiIntent()
    data class EmailChanged(val email: String) : RegistrationUiIntent()
    data class PhonePrefixChanged(val prefix: String) : RegistrationUiIntent()
    data class PhoneNumberChanged(val number: String) : RegistrationUiIntent()
    data object ClickDateBox : RegistrationUiIntent()
    data class DateSelected(val date: String) : RegistrationUiIntent()
    data object DismissDatePicker : RegistrationUiIntent()
    data class PasswordChanged(val password: String) : RegistrationUiIntent()
    data class ConfirmPasswordChanged(val password: String) : RegistrationUiIntent()
    data object SubmitRegistration : RegistrationUiIntent()
}

sealed class RegistrationUiEffect : BaseContract.UiEffect {
    data object NavigateToHome : RegistrationUiEffect()
    data class ShowError(val message: String) : RegistrationUiEffect()
}