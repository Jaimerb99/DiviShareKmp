package com.jrb.divishare.domain.repo

import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.model.Group
import com.jrb.divishare.domain.model.GroupBalance

interface GroupRepository {
    // Obtiene la lista de grupos a los que pertenezco (Puede ser un Flow si luego usamos Room para caché)
    suspend fun getMyGroups(userId: Int): DiviResult<List<Group>>

    // Crea un nuevo grupo en la base de datos
    suspend fun createGroup(name: String, currency: String, inviteCode: String): DiviResult<Group>

    // Obtiene quién debe a quién en un grupo (la vista mágica de Supabase)
    suspend fun getGroupBalances(groupId: Int): DiviResult<List<GroupBalance>>
}