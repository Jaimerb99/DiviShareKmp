package com.jrb.divishare.presentation.screens.splash

import androidx.lifecycle.viewModelScope
import com.jrb.divishare.domain.usecase.GetInitialDestinationUseCase
import com.jrb.divishare.domain.usecase.InitialDestination
import com.jrb.divishare.presentation.common.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val getInitialDestinationUseCase: GetInitialDestinationUseCase
) : BaseViewModel<SplashUiState, SplashUiIntent, SplashUiEffect>() {

    override val initialState = SplashUiState()

    override fun reduce(intent: SplashUiIntent) {
        when (intent) {
            is SplashUiIntent.CheckDestination -> checkDestination()
        }
    }

    private fun checkDestination() {
        viewModelScope.launch {
            delay(1500)

            val destination = getInitialDestinationUseCase()

            when (destination) {
                InitialDestination.LOGIN -> sendEffect(SplashUiEffect.NavigateToLogin)
                InitialDestination.HOME -> sendEffect(SplashUiEffect.NavigateToHome)
            }
        }
    }
}