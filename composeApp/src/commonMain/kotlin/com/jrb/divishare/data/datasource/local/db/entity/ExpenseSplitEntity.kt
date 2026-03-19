package com.jrb.divishare.data.datasource.local.db.entity

import androidx.room.Entity
import com.jrb.divishare.domain.model.ExpenseSplit

@Entity(tableName = "expense_split_table", primaryKeys = ["expenseId", "userId"])
data class ExpenseSplitEntity(
    val expenseId: Int,
    val userId: Int,
    val amountOwed: Double
)

fun ExpenseSplitEntity.toDomain(): ExpenseSplit = ExpenseSplit(
    expenseId = expenseId,
    userId = userId,
    amountOwed = amountOwed
)

fun ExpenseSplit.toEntity(): ExpenseSplitEntity = ExpenseSplitEntity(
    expenseId = expenseId,
    userId = userId,
    amountOwed = amountOwed
)