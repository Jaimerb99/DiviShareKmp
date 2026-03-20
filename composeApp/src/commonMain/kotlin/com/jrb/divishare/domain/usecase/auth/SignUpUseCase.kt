package com.jrb.divishare.domain.usecase.auth

import com.jrb.divishare.data.datasource.local.preferences.PreferencesLocalDataSource
import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.repo.UserRepository

class SignUpUseCase(
    private val repository: UserRepository,
    private val preferences: PreferencesLocalDataSource
) {
    suspend operator fun invoke(email: String, password: String, fullName: String): DiviResult<Unit> {
        val result = repository.signUpWithEmail(email, password, fullName)
        if (result is DiviResult.Success) {
            preferences.setHasSeenOnboarding(true)
        }
        return result
    }
}