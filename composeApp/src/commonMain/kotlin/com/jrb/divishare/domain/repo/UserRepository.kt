package com.jrb.divishare.domain.repo

import com.jrb.divishare.domain.model.DiviResult

interface UserRepository {
    suspend fun signUpWithEmail(email: String, password: String, fullName: String): DiviResult<Unit>
    suspend fun deactivateUser(userId: String): DiviResult<Unit>

    suspend fun sendRecoveryEmail(email: String): DiviResult<Unit>
    suspend fun updatePassword(newPassword: String, token: String): DiviResult<Unit>
}