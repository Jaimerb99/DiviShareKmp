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

class GroupRepositoryImpl : GroupRepository {

    private val client = SupabaseClient.httpClient

    override suspend fun getMyGroups(userId: Int): DiviResult<List<Group>> {
        return try {
            // Hacemos el GET equivalente a la petición 3 de Postman
            val response = client.get("groups") {
                // Select mágico de PostgREST para traer solo mis grupos
                parameter("select", "*,group_members!inner(*)")
                parameter("group_members.user_id", "eq.$userId")
            }

            // Convertimos el JSON directamente a nuestra lista de Data Classes
            val groups: List<Group> = response.body()
            DiviResult.Success(groups)

        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }

    override suspend fun createGroup(name: String, currency: String, inviteCode: String): DiviResult<Group> {
        // Lo implementaremos cuando hagamos la pantalla de crear grupo
        TODO("Not yet implemented")
    }

    override suspend fun getGroupBalances(groupId: Int): DiviResult<List<GroupBalance>> {
        return try {
            // Hacemos el GET a nuestra vista SQL 'group_balances'
            val response = client.get("group_balances") {
                parameter("group_id", "eq.$groupId")
            }

            val balances: List<GroupBalance> = response.body()
            DiviResult.Success(balances)

        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }
}