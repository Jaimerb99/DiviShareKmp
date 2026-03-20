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

    override suspend fun getExpensesForGroup(groupId: Int): DiviResult<List<Expense>> {
        return try {
            val response = client.get("expenses") {
                parameter("group_id", "eq.$groupId")
                parameter("select", "*,profiles!expenses_paid_by_fkey(*),expense_splits(*)")
            }
            DiviResult.Success(response.body())
        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }

    override suspend fun createExpense(expense: Expense, splits: List<ExpenseSplit>): DiviResult<Unit> {
        return try {
            // 1. Guardamos el Gasto y RECUPERAMOS el objeto devuelto por Supabase
            // Como Ktor parsea arrays por defecto cuando usamos return=representation, le pedimos un List y cogemos el primero
            val createdExpenses: List<Expense> = client.post("expenses") {
                setBody(expense)
            }.body()

            val newExpenseId = createdExpenses.first().id

            // 2. Actualizamos los splits para que apunten al ID que acaba de generar la Base de Datos
            val splitsWithCorrectId = splits.map { it.copy(expenseId = newExpenseId) }

            // 3. Guardamos la división del gasto (Los Splits)
            client.post("expense_splits") {
                setBody(splitsWithCorrectId)
            }

            DiviResult.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            DiviResult.Error(DiviError.NetworkError)
        }
    }

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