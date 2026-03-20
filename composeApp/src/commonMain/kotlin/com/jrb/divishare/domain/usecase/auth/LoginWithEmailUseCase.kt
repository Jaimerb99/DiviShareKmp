package com.jrb.divishare.domain.usecase.auth

import com.jrb.divishare.data.datasource.local.preferences.PreferencesLocalDataSource
import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.repo.UserRepository
class LoginUseCase(
    private val repository: UserRepository,
    private val preferences: PreferencesLocalDataSource
) {
    suspend operator fun invoke(email: String, password: String): DiviResult<Unit> {
        val result = repository.loginWithEmail(email, password)

        return when (result) {
            is DiviResult.Success -> {
                // 1. Guardamos el ID del usuario para futuras peticiones
                preferences.setUserId(result.data)
                // 2. Marcamos el onboarding como visto
                preferences.setHasSeenOnboarding(true)
                DiviResult.Success(Unit)
            }
            is DiviResult.Error -> DiviResult.Error(result.error)
        }
    }
}