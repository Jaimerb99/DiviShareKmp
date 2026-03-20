package com.jrb.divishare.domain.usecase.auth

import com.jrb.divishare.data.datasource.local.preferences.PreferencesLocalDataSource

class CheckOnboardingStatusUseCase(
    private val preferencesLocalDataSource: PreferencesLocalDataSource
) {
    /**
     * Retorna true si el usuario ya ha completado el onboarding.
     */
    suspend operator fun invoke(): Boolean {
        return preferencesLocalDataSource.getHasSeenOnboarding()
    }
}