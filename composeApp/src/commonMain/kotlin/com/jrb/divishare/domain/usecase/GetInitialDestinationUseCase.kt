package com.jrb.divishare.domain.usecase

import com.jrb.divishare.data.datasource.local.preferences.PreferencesLocalDataSource


enum class InitialDestination {
    LOGIN, HOME
}

class GetInitialDestinationUseCase(
    private val preferencesLocalDataSource: PreferencesLocalDataSource
) {
    suspend operator fun invoke(): InitialDestination {
        val isLoggedIn = preferencesLocalDataSource.isUserLoggedIn()

        return if (isLoggedIn) {
            InitialDestination.HOME
        } else {
            InitialDestination.LOGIN
        }
    }
}