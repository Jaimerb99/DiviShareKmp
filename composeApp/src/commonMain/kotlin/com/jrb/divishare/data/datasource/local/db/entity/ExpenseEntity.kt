package com.jrb.divishare.data.datasource.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jrb.divishare.domain.model.Expense

@Entity(tableName = "expense_table")
data class ExpenseEntity(
    @PrimaryKey val id: Int,
    val groupId: Int,
    val paidBy: String,
    val description: String,
    val category: String,
    val totalAmount: Double,
    val date: String?
)

fun ExpenseEntity.toDomain(): Expense = Expense(
    id = id,
    groupId = groupId,
    paidBy = paidBy,
    description = description,
    category = category,
    totalAmount = totalAmount,
    date = date
)

fun Expense.toEntity(): ExpenseEntity = ExpenseEntity(
    id = id,
    groupId = groupId,
    paidBy = paidBy,
    description = description,
    category = category,
    totalAmount = totalAmount,
    date = date
)