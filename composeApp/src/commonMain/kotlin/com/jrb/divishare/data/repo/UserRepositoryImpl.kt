package com.jrb.divishare.data.repo

import com.jrb.divishare.data.remote.SupabaseClient
import com.jrb.divishare.domain.model.AuthResponse
import com.jrb.divishare.domain.model.DiviError
import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.model.LoginRequest
import com.jrb.divishare.domain.repo.UserRepository
import io.ktor.client.call.body
import io.ktor.client.request.patch
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Clases auxiliares para enviar el JSON de registro exacto que pide Supabase
@Serializable
private data class SignUpRequest(
    val email: String,
    val password: String,
    val data: UserMetaData
)

@Serializable
private data class UserMetaData(
    @SerialName("full_name") val fullName: String,
    @SerialName("avatar_url") val avatarUrl: String = ""
)

// Clase auxiliar para el Soft Delete
@Serializable
private data class DeactivateUserRequest(
    @SerialName("is_active") val isActive: Boolean = false
)

@Serializable
private data class RecoverPasswordRequest(val email: String)

@Serializable
private data class UpdatePasswordRequest(val password: String)

class UserRepositoryImpl : UserRepository {

    private val client = SupabaseClient.httpClient
    private val projectId = "aipokcjzeehtprwouylk"

    override suspend fun signUpWithEmail(email: String, password: String, fullName: String): DiviResult<Unit> {
        return try {
            val requestBody = SignUpRequest(
                email = email,
                password = password,
                data = UserMetaData(fullName = fullName)
            )

            // Usamos la URL completa porque esta ruta es de Auth, no de Rest
            client.post("https://$projectId.supabase.co/auth/v1/signup") {
                setBody(requestBody)
            }

            DiviResult.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }

    override suspend fun deactivateUser(userId: String): DiviResult<Unit> {
        return try {
            // Hacemos el PATCH a la tabla profiles para poner is_active a false
            client.patch("profiles") {
                parameter("id", "eq.$userId")
                setBody(DeactivateUserRequest(isActive = false))
            }

            DiviResult.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }

    override suspend fun sendRecoveryEmail(email: String): DiviResult<Unit> {
        return try {
            client.post("https://$projectId.supabase.co/auth/v1/recover") {
                setBody(RecoverPasswordRequest(email))
            }
            DiviResult.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }

    override suspend fun updatePassword(newPassword: String, token: String): DiviResult<Unit> {
        return try {
            // Hacemos un PUT a la ruta /user para actualizar sus datos
            // Usamos un client.request manual para poder inyectarle el Token específico de esta operación
            client.put("https://$projectId.supabase.co/auth/v1/user") {
                // Sobreescribimos el Authorization solo para esta llamada usando el token que le pasamos
                headers.remove("Authorization")
                headers.append("Authorization", "Bearer $token")
                setBody(UpdatePasswordRequest(newPassword))
            }
            DiviResult.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }

    override suspend fun loginWithEmail(email: String, password: String): DiviResult<String> {
        return try {
            val requestBody = LoginRequest(email = email, password = password)
            val response: AuthResponse = client.post("https://$projectId.supabase.co/auth/v1/token") {
                parameter("grant_type", "password")
                setBody(requestBody)
            }.body()

            // Devolvemos el ID que viene en el objeto user de la respuesta de Supabase
            DiviResult.Success(response.user.id)
        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }
}