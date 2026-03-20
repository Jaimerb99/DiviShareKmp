package com.jrb.divishare.data.repository

import com.jrb.divishare.data.repo.ExpenseRepositoryImpl
import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.model.Expense
import com.jrb.divishare.domain.model.ExpenseSplit
import com.jrb.divishare.domain.model.Settlement
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.random.Random

class ExpenseRepositoryImplTest {

    private val repository = ExpenseRepositoryImpl()

     private val user1Id = "11111111-1111-1111-1111-111111111111"
    private val user2Id = "22222222-2222-2222-2222-222222222222"

    @Test
    fun testGetExpensesForGroup_returnsSuccess() = runTest {
        val result = repository.getExpensesForGroup(groupId = 1)

        assertTrue(result is DiviResult.Success, "Error al obtener los gastos de Supabase")

        val expenses = result.data
        println("✅ Gastos del grupo 1:")
        expenses.forEach { expense ->
            println(" - Ticket: ${expense.description} | Pagó: ${expense.paidByUser?.name} | Total: ${expense.totalAmount}€")
            println("   Divisiones: ${expense.splits}")
        }
    }

    @Test
    fun testCreateExpenseAndSplits_insertsSuccessfully() = runTest {
        val randomExpenseId = Random.nextInt(100, 10000)

        // Usamos los Strings (UUID) en lugar de los Ints
        val newExpense = Expense(
            id = randomExpenseId,
            groupId = 1,
            paidBy = user1Id,
            description = "Cena de Prueba desde Ktor",
            category = "comida",
            totalAmount = 50.00
        )

        val newSplits = listOf(
            ExpenseSplit(expenseId = randomExpenseId, userId = user1Id, amountOwed = 25.0),
            ExpenseSplit(expenseId = randomExpenseId, userId = user2Id, amountOwed = 25.0)
        )

        val result = repository.createExpense(newExpense, newSplits)

        assertTrue(result is DiviResult.Success, "Falló la creación del gasto o de sus divisiones")
        println("✅ Gasto de prueba ($randomExpenseId) creado exitosamente en la BD")
    }

    @Test
    fun testSettleDebt_insertsSuccessfully() = runTest {
        val randomSettlementId = Random.nextInt(100, 10000)

        val settlement = Settlement(
            id = randomSettlementId,
            groupId = 1,
            paidBy = user2Id,
            paidTo = user1Id,
            amount = 10.00
        )

        val result = repository.settleDebt(settlement)

        assertTrue(result is DiviResult.Success, "Falló el registro del pago (Settlement)")
        println("✅ Pago directo de prueba ($randomSettlementId) registrado con éxito")
    }
}