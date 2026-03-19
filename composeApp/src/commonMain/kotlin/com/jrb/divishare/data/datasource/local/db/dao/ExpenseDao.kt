package com.jrb.divishare.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jrb.divishare.data.datasource.local.db.entity.ExpenseEntity
import com.jrb.divishare.data.datasource.local.db.entity.ExpenseSplitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Upsert
    suspend fun upsertExpenses(expenses: List<ExpenseEntity>)

    @Upsert
    suspend fun upsertExpenseSplits(splits: List<ExpenseSplitEntity>)

    @Query("SELECT * FROM expense_table WHERE groupId = :groupId ORDER BY date DESC")
    fun getExpensesForGroup(groupId: Int): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expense_split_table WHERE expenseId = :expenseId")
    suspend fun getSplitsForExpense(expenseId: Int): List<ExpenseSplitEntity>

    @Query("DELETE FROM expense_table")
    suspend fun deleteAllExpenses()
}