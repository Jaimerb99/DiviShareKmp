package com.jrb.divishare.domain.usecase.auth

import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.repo.UserRepository

class SendRecoveryEmailUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String): DiviResult<Unit> {
        return userRepository.sendRecoveryEmail(email)
    }
}