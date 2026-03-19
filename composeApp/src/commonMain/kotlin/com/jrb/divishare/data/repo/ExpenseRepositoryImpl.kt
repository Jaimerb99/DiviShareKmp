package com.jrb.divishare.data.repo

import com.jrb.divishare.data.remote.SupabaseClient
import com.jrb.divishare.domain.model.DiviError
import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.model.Expense
import com.jrb.divishare.domain.model.ExpenseSplit
import com.jrb.divishare.domain.model.Settlement
import com.jrb.divishare.domain.repo.ExpenseRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class ExpenseRepositoryImpl : ExpenseRepository {

    private val client = SupabaseClient.httpClient

    // Llamada 5 de Postman: Obtener gastos de un grupo
    override suspend fun getExpensesForGroup(groupId: Int): DiviResult<List<Expense>> {
        return try {
            val response = client.get("expenses") {
                parameter("group_id", "eq.$groupId")
                // El select complejo para traer el nombre del que pagó y los splits
                parameter("select", "*,profiles!expenses_paid_by_fkey(*),expense_splits(*)")
            }
            DiviResult.Success(response.body())
        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }

    // Llamada 6 y 7 de Postman: Crear Gasto y sus Splits
    override suspend fun createExpense(expense: Expense, splits: List<ExpenseSplit>): DiviResult<Unit> {
        return try {
            // 1. Guardamos el Gasto
            client.post("expenses") {
                setBody(expense)
            }

            // 2. Guardamos la división del gasto (Los Splits) enviando la lista de golpe
            client.post("expense_splits") {
                setBody(splits)
            }

            DiviResult.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }

    // Llamada 9 de Postman: Saldar deuda
    override suspend fun settleDebt(settlement: Settlement): DiviResult<Unit> {
        return try {
            client.post("settlements") {
                setBody(settlement)
            }
            DiviResult.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }
}