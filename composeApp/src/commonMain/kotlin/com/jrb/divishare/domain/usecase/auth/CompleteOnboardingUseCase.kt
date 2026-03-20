package com.jrb.divishare.domain.usecase.auth

import com.jrb.divishare.data.datasource.local.preferences.PreferencesLocalDataSource


class CompleteOnboardingUseCase(
    private val preferencesLocalDataSource: PreferencesLocalDataSource
) {
    suspend operator fun invoke() {
        preferencesLocalDataSource.setHasSeenOnboarding(true)
    }
}