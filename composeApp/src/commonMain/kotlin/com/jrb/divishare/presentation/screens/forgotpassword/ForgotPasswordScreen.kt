package com.jrb.divishare.presentation.screens.forgotpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jrb.divishare.presentation.component.TextButtonBox
import com.jrb.divishare.presentation.theme.DiviShareTheme
import com.jrb.divishare.ui.components.BackToolbar
import com.jrb.divishare.ui.components.EmailTextField
import divishare.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = koinViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.stateStateFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                is ForgotPasswordUiEffect.NavigateBack -> onBack()
                is ForgotPasswordUiEffect.ShowError -> { /* Snackbar logic */ }
            }
        }
    }

    ForgotPasswordContent(
        state = state,
        onIntent = { viewModel.sendIntent(it) },
        onBack = onBack
    )
}

@Composable
private fun ForgotPasswordContent(
    state: ForgotPasswordUiState,
    onIntent: (ForgotPasswordUiIntent) -> Unit,
    onBack: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            },
        topBar = {
            Box(modifier = Modifier.padding(top = 10.dp)) {
                BackToolbar(title = "", onBack = onBack)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Sección Superior (Instrucciones)
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(Res.string.reset_password_title),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(Res.string.reset_password_instruction),
                    style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 20.sp),
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }

            // Sección Inferior (Formulario + Imagen)
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(4f)
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(20.dp)
            ) {
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = stringResource(Res.string.enter_email_label),
                    modifier = Modifier.padding(start = 5.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                EmailTextField(
                    value = state.email,
                    onValueChange = { onIntent(ForgotPasswordUiIntent.EmailChanged(it)) },
                    inverted = true
                )

                Spacer(modifier = Modifier.height(40.dp))

                TextButtonBox(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(200.dp)
                        .height(48.dp),
                    text = stringResource(Res.string.recover_button),
                    enabled = state.isButtonEnabled && !state.isLoading,
                    onClick = { onIntent(ForgotPasswordUiIntent.SubmitRecovery) }
                )

                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(resource = Res.drawable.ic_empty_inbox),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview
@Composable
private fun ForgotPasswordPreview() {
    DiviShareTheme {
        ForgotPasswordContent(
            state = ForgotPasswordUiState(email = "user@example.com"),
            onIntent = {},
            onBack = {}
        )
    }
}