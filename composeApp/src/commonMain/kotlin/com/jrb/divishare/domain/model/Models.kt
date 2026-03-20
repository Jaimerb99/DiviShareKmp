package com.jrb.divishare.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// --- 1. USUARIOS Y PERFILES ---
@Serializable
data class Profile(
    val id: String,
    val name: String,
    val email: String? = null,
    val phone: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null
)

// --- 2. GRUPOS (PROYECTOS) ---
@Serializable
data class Group(
    val id: Int,
    val name: String,
    val currency: String = "EUR",
    @SerialName("invite_code") val inviteCode: String? = null,
    @SerialName("created_at") val createdAt: String? = null
)

// --- 3. GASTOS (TICKETS) ---
@Serializable
data class Expense(
    val id: Int,
    @SerialName("group_id") val groupId: Int,
    @SerialName("paid_by") val paidBy: String,
    val description: String,
    val category: String = "general",
    @SerialName("total_amount") val totalAmount: Double,
    val date: String? = null,

    @SerialName("profiles") val paidByUser: Profile? = null,
    @SerialName("expense_splits") val splits: List<ExpenseSplit> = emptyList()
)

// --- 4. CÓMO SE DIVIDE UN GASTO ---
@Serializable
data class ExpenseSplit(
    @SerialName("expense_id") val expenseId: Int,
    @SerialName("user_id") val userId: String,
    @SerialName("amount_owed") val amountOwed: Double
)

// --- 5. PAGOS DIRECTOS (SALDAR DEUDAS) ---
@Serializable
data class Settlement(
    val id: Int,
    @SerialName("group_id") val groupId: Int,
    @SerialName("paid_by") val paidBy: String,
    @SerialName("paid_to") val paidTo: String,
    val amount: Double,
    val date: String? = null
)

// --- 6. BALANCES GLOBALES (LA PANTALLA PRINCIPAL) ---
// Esto mapea exactamente a la VISTA SQL 'group_balances' que creamos
@Serializable
data class GroupBalance(
    @SerialName("group_id") val groupId: Int,
    @SerialName("user_id") val userId: String,
    @SerialName("user_name") val userName: String,
    @SerialName("total_paid") val totalPaid: Double,
    @SerialName("total_consumed") val totalConsumed: Double,
    @SerialName("total_sent") val totalSent: Double,
    @SerialName("total_received") val totalReceived: Double,
    val balance: Double
)