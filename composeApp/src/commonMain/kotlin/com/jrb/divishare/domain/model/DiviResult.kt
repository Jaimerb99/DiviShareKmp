package com.jrb.divishare.domain.model

sealed interface DiviResult<out T> {
    data class Success<T>(val data: T) : DiviResult<T>
    data class Error(val error: DiviError) : DiviResult<Nothing>
}