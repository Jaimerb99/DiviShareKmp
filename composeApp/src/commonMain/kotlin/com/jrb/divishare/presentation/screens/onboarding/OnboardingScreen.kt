package com.jrb.divishare.presentation.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jrb.divishare.presentation.component.TextButtonBox
import com.jrb.divishare.presentation.theme.DiviShareTheme
import divishare.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = koinViewModel(),
    onNavigateToLogin: () -> Unit
) {
    val state by viewModel.stateStateFlow.collectAsState()
    val pagerState = rememberPagerState(pageCount = { state.pages.size })

    LaunchedEffect(Unit) {
        viewModel.sendIntent(OnboardingUiIntent.LoadPages)
        viewModel.effectFlow.collect { effect ->
            if (effect is OnboardingUiEffect.NavigateToLogin) onNavigateToLogin()
        }
    }

    // Sincronizar el pager con el estado del ViewModel
    LaunchedEffect(state.currentPage) {
        if (state.pages.isNotEmpty()) {
            pagerState.animateScrollToPage(state.currentPage)
        }
    }

    OnboardingContent(
        state = state,
        onIntent = { viewModel.sendIntent(it) }
    )
}

@Composable
private fun OnboardingContent(
    state: OnboardingUiState,
    onIntent: (OnboardingUiIntent) -> Unit
) {
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { onIntent(OnboardingUiIntent.ClickSkip) }
                ) {
                    Text(stringResource(Res.string.onboarding_skip))
                }

                TextButtonBox(
                    modifier = Modifier.width(150.dp).height(48.dp),
                    text = if (state.currentPage == state.pages.size - 1)
                        stringResource(Res.string.onboarding_get_started)
                    else stringResource(Res.string.onboarding_next),
                    onClick = { onIntent(OnboardingUiIntent.ClickNext) }
                )
            }
        }
    ) { padding ->
        if (state.pages.isNotEmpty()) {
            val page = state.pages[state.currentPage]
            Column(
                modifier = Modifier.fillMaxSize().padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(page.image),
                    contentDescription = null,
                    modifier = Modifier.size(280.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = page.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = page.description,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 40.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun OnboardingPreview() {
    DiviShareTheme {
        OnboardingContent(OnboardingUiState(), {})
    }
}