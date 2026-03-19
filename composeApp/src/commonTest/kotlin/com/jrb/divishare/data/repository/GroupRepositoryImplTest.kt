package com.jrb.divishare.data.repository

import com.jrb.divishare.data.repo.GroupRepositoryImpl
import com.jrb.divishare.domain.model.DiviResult
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class GroupRepositoryImplTest {

    // Instanciamos el repositorio que acabamos de crear
    private val repository = GroupRepositoryImpl()

    @Test
    fun testGetMyGroups_returnsSuccessAndData() = runTest {
        // 1. Ejecutamos la llamada a Supabase pidiendo los grupos del usuario 1
        val result = repository.getMyGroups(userId = 1)

        // 2. Comprobamos que el resultado sea un 'Success' y no un 'Error'
        assertTrue(result is DiviResult.Success, "La llamada a Supabase falló o dio error de red")

        // 3. Extraemos los datos y comprobamos que no estén vacíos
        val groups = result.data
        println("✅ Respuesta de Supabase: $groups")

        assertTrue(groups.isNotEmpty(), "La lista de grupos llegó vacía. ¿Añadiste grupos en Supabase?")

        // Si quieres, puedes ser más específico:
        // assertEquals("Viaje a Londres", groups.first().name)
    }

    @Test
    fun testGetGroupBalances_returnsSuccess() = runTest {
        // Probamos también la vista matemática de los saldos para el grupo 1
        val result = repository.getGroupBalances(groupId = 1)

        assertTrue(result is DiviResult.Success, "Falló la consulta de saldos")
        println("✅ Saldos del grupo: ${result.data}")
    }
}