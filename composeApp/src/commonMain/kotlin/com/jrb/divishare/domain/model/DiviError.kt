package com.jrb.divishare.domain.model

sealed interface DiviError {
    data object NetworkError : DiviError
    data object Unauthorized : DiviError
    data object Unknown : DiviError
    data class Custom(val message: String) : DiviError
}