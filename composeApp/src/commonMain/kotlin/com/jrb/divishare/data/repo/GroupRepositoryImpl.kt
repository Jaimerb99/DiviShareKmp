package com.jrb.divishare.data.repo

import com.jrb.divishare.data.remote.SupabaseClient
import com.jrb.divishare.domain.model.DiviError
import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.model.Group
import com.jrb.divishare.domain.model.GroupBalance
import com.jrb.divishare.domain.repo.GroupRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Clases auxiliares para enviar el JSON a Ktor tal cual lo tienes en Postman (Sin el ID)
@Serializable
private data class CreateGroupRequest(
    val name: String,
    val currency: String,
    @SerialName("invite_code") val inviteCode: String
)

@Serializable
private data class AddMemberRequest(
    @SerialName("group_id") val groupId: Int,
    @SerialName("user_id") val userId: String
)

class GroupRepositoryImpl : GroupRepository {

    private val client = SupabaseClient.httpClient

    override suspend fun getMyGroups(userId: String): DiviResult<List<Group>> {
        return try {
            val response = client.get("groups") {
                parameter("select", "*,group_members!inner(*)")
                parameter("group_members.user_id", "eq.$userId")
            }
            DiviResult.Success(response.body())
        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }

    override suspend fun createGroup(
        name: String,
        currency: String,
        inviteCode: String,
        creatorUserId: String
    ): DiviResult<Group> {
        return try {
            val newGroupRequest = CreateGroupRequest(name, currency, inviteCode)

            // Gracias a la cabecera "Prefer: return=representation", nos devuelve el grupo con su ID real
            val createdGroups: List<Group> = client.post("groups") {
                setBody(newGroupRequest)
            }.body()

            val newGroup = createdGroups.first()

            // 2. Metemos al creador como miembro de ese nuevo grupo
            val memberRequest = AddMemberRequest(
                groupId = newGroup.id, // El ID recién generado por la base de datos
                userId = creatorUserId
            )

            client.post("group_members") {
                setBody(memberRequest)
            }

            DiviResult.Success(newGroup)

        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }

    override suspend fun getGroupBalances(groupId: Int): DiviResult<List<GroupBalance>> {
        return try {
            val response = client.get("group_balances") {
                parameter("group_id", "eq.$groupId")
            }
            DiviResult.Success(response.body())
        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }
}