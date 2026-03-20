package com.jrb.divishare.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jrb.divishare.presentation.component.TextButtonBox
import com.jrb.divishare.presentation.theme.DiviShareTheme
import com.jrb.divishare.ui.components.EmailTextField
import com.jrb.divishare.ui.components.LoginToolbar
import com.jrb.divishare.ui.components.PasswordTextField
import divishare.composeapp.generated.resources.Res
import divishare.composeapp.generated.resources.dont_have_account
import divishare.composeapp.generated.resources.email
import divishare.composeapp.generated.resources.forgot_password
import divishare.composeapp.generated.resources.log_in
import divishare.composeapp.generated.resources.login_image_desc
import divishare.composeapp.generated.resources.logini_image
import divishare.composeapp.generated.resources.password
import divishare.composeapp.generated.resources.sign_up
import divishare.composeapp.generated.resources.welcome_aboard
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onNavigateToOnboarding: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val state by viewModel.stateStateFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                is LoginUiEffect.NavigateToOnboarding -> onNavigateToOnboarding()
                is LoginUiEffect.NavigateToHome -> onNavigateToHome()
                is LoginUiEffect.NavigateToForgotPassword -> onNavigateToForgotPassword()
                is LoginUiEffect.NavigateToRegister -> onNavigateToRegister()
                is LoginUiEffect.ShowError -> {}
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sendIntent(LoginUiIntent.CheckOnboardingStatus)
    }

    if (!state.isCheckingOnboarding) {
        LoginContent(
            state = state,
            onIntent = { viewModel.sendIntent(it) }
        )
    } else {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background))
    }
}

@Composable
fun LoginContent(
    state: LoginUiState,
    onIntent: (LoginUiIntent) -> Unit
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            }
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            LoginToolbar()
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.weight(0.1f))

                Text(
                    text = stringResource(Res.string.welcome_aboard),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.fillMaxWidth()
                )

                Image(
                    painter = painterResource(resource = Res.drawable.logini_image),
                    contentDescription = stringResource(Res.string.login_image_desc),
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = stringResource(Res.string.email),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 5.dp)
                )

                EmailTextField(
                    value = state.email,
                    onValueChange = { onIntent(LoginUiIntent.EmailChanged(it)) }
                )

                Spacer(modifier = Modifier.weight(0.1f))

                Text(
                    text = stringResource(Res.string.password),
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = 5.dp)
                )

                PasswordTextField(
                    value = state.password,
                    onValueChange = { onIntent(LoginUiIntent.PasswordChanged(it)) },
                    error = false
                )

                Spacer(modifier = Modifier.weight(0.3f))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButtonBox(
                        modifier = Modifier.width(180.dp).height(40.dp),
                        text = stringResource(Res.string.log_in),
                        enabled = state.isLoginButtonEnabled,
                        onClick = { onIntent(LoginUiIntent.SubmitLogin) }
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        text = stringResource(Res.string.forgot_password),
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = DiviShareTheme.colors.info,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { onIntent(LoginUiIntent.ClickForgotPassword) }
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = buildAnnotatedString {
                        append(stringResource(Res.string.dont_have_account))
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append(stringResource(Res.string.sign_up))
                        }
                    },
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { onIntent(LoginUiIntent.ClickSignUp) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    DiviShareTheme {
        LoginContent(
            state = LoginUiState(
                email = "example@divishare.com",
                password = "password123",
                isLoginButtonEnabled = true
            ),
            onIntent = {}
        )
    }
}