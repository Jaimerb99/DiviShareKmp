package com.jrb.divishare.domain.usecase.auth

import com.jrb.divishare.data.datasource.local.preferences.PreferencesLocalDataSource

class GetUserIdUseCase(
    private val preferences: PreferencesLocalDataSource
) {
    /**
     * Recupera el ID del usuario persistido tras el login/registro.
     * Retorna null si no hay un usuario autenticado.
     */
    suspend operator fun invoke(): String? {
        return preferences.getUserId()
    }
}