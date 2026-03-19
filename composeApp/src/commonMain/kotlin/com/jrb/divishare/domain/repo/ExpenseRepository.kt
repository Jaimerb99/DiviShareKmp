package com.jrb.divishare.domain.repo

import com.jrb.divishare.domain.model.DiviResult
import com.jrb.divishare.domain.model.Expense
import com.jrb.divishare.domain.model.ExpenseSplit
import com.jrb.divishare.domain.model.Settlement

interface ExpenseRepository {
    // Obtiene el listado de tickets de un grupo
    suspend fun getExpensesForGroup(groupId: Int): DiviResult<List<Expense>>

    // Guarda un gasto y cómo se divide entre los miembros
    suspend fun createExpense(expense: Expense, splits: List<ExpenseSplit>): DiviResult<Unit>

    // Registra un pago directo para saldar una deuda
    suspend fun settleDebt(settlement: Settlement): DiviResult<Unit>
}