package com.jrb.divishare.presentation.screens.onboarding

import androidx.lifecycle.viewModelScope
import com.jrb.divishare.domain.usecase.auth.CompleteOnboardingUseCase
import com.jrb.divishare.presentation.common.BaseViewModel
import divishare.composeapp.generated.resources.*
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

class OnboardingViewModel(
    private val completeOnboardingUseCase: CompleteOnboardingUseCase
) : BaseViewModel<OnboardingUiState, OnboardingUiIntent, OnboardingUiEffect>() {

    override val initialState = OnboardingUiState()

    override fun reduce(intent: OnboardingUiIntent) {
        when (intent) {
            is OnboardingUiIntent.LoadPages -> loadPages()
            is OnboardingUiIntent.ClickNext -> handleNext()
            is OnboardingUiIntent.ClickSkip -> finishOnboarding()
        }
    }

    private fun loadPages() {
        viewModelScope.launch {
            val pages = listOf(
                OnboardingPage(getString(Res.string.onboarding_title_1), getString(Res.string.onboarding_desc_1), Res.drawable.logini_image),
                OnboardingPage(getString(Res.string.onboarding_title_2), getString(Res.string.onboarding_desc_2), Res.drawable.logini_image),
                OnboardingPage(getString(Res.string.onboarding_title_3), getString(Res.string.onboarding_desc_3), Res.drawable.logini_image)
            )
            updateState { copy(pages = pages) }
        }
    }

    private fun handleNext() {
        val isLastPage = currentState.currentPage >= currentState.pages.size - 1

        if (!isLastPage) {
            updateState { copy(currentPage = currentPage + 1) }
        } else {
            finishOnboarding()
        }
    }

    private fun finishOnboarding() {
        viewModelScope.launch {
            completeOnboardingUseCase()
            sendEffect(OnboardingUiEffect.NavigateToLogin)
        }
    }
}