package com.jrb.divishare.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jrb.divishare.presentation.theme.DiviShareTheme
import divishare.composeapp.generated.resources.Res
import divishare.composeapp.generated.resources.*

import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.effectFlow.collect { effect ->
            when (effect) {
                is SplashUiEffect.NavigateToLogin -> onNavigateToLogin()
                is SplashUiEffect.NavigateToHome -> onNavigateToHome()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sendIntent(SplashUiIntent.CheckDestination)
    }

    SplashContent()
}

@Composable
fun SplashContent() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.padding(start = 100.dp, end = 70.dp)
        ) {
            Image(
                painter = painterResource(Res.drawable.logo),
                contentDescription = "App Logo",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Fit,
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = stringResource(Res.string.app_name),
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    DiviShareTheme {
        SplashContent()
    }
}