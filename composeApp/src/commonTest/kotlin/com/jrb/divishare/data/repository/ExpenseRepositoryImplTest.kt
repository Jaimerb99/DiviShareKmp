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

    @Test
    fun testGetExpensesForGroup_returnsSuccess() = runTest {
        // Probamos la Llamada 5: Sacar los tickets del grupo 1
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
        // Generamos un ID aleatorio alto para no chocar con los que ya creaste en Postman
        val randomExpenseId = Random.nextInt(100, 10000)

        // Preparamos los datos (Llamada 6 y 7 combinadas)
        val newExpense = Expense(
            id = randomExpenseId,
            groupId = 1,
            paidBy = 1, // Paga el usuario 1
            description = "Cena de Prueba desde Ktor",
            category = "comida",
            totalAmount = 50.00
        )

        val newSplits = listOf(
            ExpenseSplit(expenseId = randomExpenseId, userId = 1, amountOwed = 25.0),
            ExpenseSplit(expenseId = randomExpenseId, userId = 2, amountOwed = 25.0)
        )

        // Ejecutamos la inserción
        val result = repository.createExpense(newExpense, newSplits)

        assertTrue(result is DiviResult.Success, "Falló la creación del gasto o de sus divisiones")
        println("✅ Gasto de prueba ($randomExpenseId) creado exitosamente en la BD")
    }

    @Test
    fun testSettleDebt_insertsSuccessfully() = runTest {
        val randomSettlementId = Random.nextInt(100, 10000)

        // Llamada 9: El usuario 2 le paga 10€ al usuario 1
        val settlement = Settlement(
            id = randomSettlementId,
            groupId = 1,
            paidBy = 2,
            paidTo = 1,
            amount = 10.00
        )

        val result = repository.settleDebt(settlement)

        assertTrue(result is DiviResult.Success, "Falló el registro del pago (Settlement)")
        println("✅ Pago directo de prueba ($randomSettlementId) registrado con éxito")
    }
}