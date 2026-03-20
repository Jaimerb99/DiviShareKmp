package com.jrb.divishare.presentation.screens.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jrb.divishare.presentation.component.DateDialog
import com.jrb.divishare.presentation.component.TextButtonBox
import com.jrb.divishare.presentation.theme.DiviShareTheme
import com.jrb.divishare.ui.components.*
import divishare.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = koinViewModel(),
    onBack: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val state by viewModel.stateStateFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                is RegistrationUiEffect.NavigateToHome -> onNavigateToHome()
                is RegistrationUiEffect.ShowError -> { /* Mostrar error */ }
            }
        }
    }

    RegistrationContent(
        state = state,
        onIntent = { viewModel.sendIntent(it) },
        onBack = onBack
    )

    if (state.isDatePickerVisible) {
        DateDialog(
            showDatePicker = true,
            onDateSelected = { viewModel.sendIntent(RegistrationUiIntent.DateSelected(it)) },
            onDismiss = { viewModel.sendIntent(RegistrationUiIntent.DismissDatePicker) }
        )
    }
}

@Composable
fun RegistrationContent(
    state: RegistrationUiState,
    onIntent: (RegistrationUiIntent) -> Unit,
    onBack: () -> Unit
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .pointerInput(Unit) { detectTapGestures(onTap = { focusManager.clearFocus() }) }
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            BackToolbar(title = stringResource(Res.string.create_account), onBack = onBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(stringResource(Res.string.name_label), modifier = Modifier.padding(start = 5.dp))
            NameTextField(state.name, { onIntent(RegistrationUiIntent.NameChanged(it)) }, stringResource(Res.string.name_placeholder))

            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(Res.string.email), modifier = Modifier.padding(start = 5.dp))
            EmailTextField(state.email, { onIntent(RegistrationUiIntent.EmailChanged(it)) })

            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(Res.string.phone_label), modifier = Modifier.padding(start = 5.dp))
            PhoneTextField(state.phonePrefix, state.phoneNumber,
                { onIntent(RegistrationUiIntent.PhonePrefixChanged(it)) },
                { onIntent(RegistrationUiIntent.PhoneNumberChanged(it)) })

            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(Res.string.birthday_label), modifier = Modifier.padding(start = 5.dp))
            DateBox(state.birthDate) { onIntent(RegistrationUiIntent.ClickDateBox) }

            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(Res.string.password), modifier = Modifier.padding(start = 5.dp))
            PasswordTextField(state.password, { onIntent(RegistrationUiIntent.PasswordChanged(it)) })

            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(Res.string.confirm_password_label), modifier = Modifier.padding(start = 5.dp))
            PasswordTextField(state.confirmPassword, { onIntent(RegistrationUiIntent.ConfirmPasswordChanged(it)) })

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = buildAnnotatedString {
                    append(stringResource(Res.string.terms_agreement_part1))
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(Res.string.terms_agreement_bold))
                    }
                },
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(horizontal = 30.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextButtonBox(
                modifier = Modifier.align(Alignment.CenterHorizontally).width(180.dp).height(45.dp),
                text = stringResource(Res.string.sign_up_button),
                enabled = state.isRegisterButtonEnabled,
                onClick = { onIntent(RegistrationUiIntent.SubmitRegistration) }
            )

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrationScreenPreview() {
    DiviShareTheme {
        RegistrationContent(
            state = RegistrationUiState(
                name = "Ana Rodríguez",
                email = "Ana@example.com",
                phonePrefix = "+34",
                phoneNumber = "600 000 000",
                birthDate = "1995-05-20",
                isRegisterButtonEnabled = false
            ),
            onIntent = {},
            onBack = {}
        )
    }
}