package com.jrb.divishare.domain.model

import divishare.composeapp.generated.resources.Res
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import divishare.composeapp.generated.resources.*

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
    val type: String = "general",
    @SerialName("invite_code") val inviteCode: String? = null,
    @SerialName("created_at") val createdAt: String? = null
) {
    // No se serializa. Mapea el string 'type' a tu recurso local.
    val icon: DrawableResource
        get() = when (type.lowercase()) {

            // ✈️ Viajes y Vacaciones
            "airplane", "flight", "trip" -> Res.drawable.ic_project_airplane
            "car", "roadtrip" -> Res.drawable.ic_project_car
            "luggage", "suitcase" -> Res.drawable.ic_project_luggage
            "bag", "backpack" -> Res.drawable.ic_project_bag
            "sun", "summer" -> Res.drawable.ic_project_sun
            "coconut_tree", "beach" -> Res.drawable.ic_project_coconut_tree
            "sunglasses" -> Res.drawable.ic_project_sunglasses

            // 🍕 Comida y Bebida
            "beer", "pub", "bar" -> Res.drawable.ic_project_beer
            "cocktail", "drinks" -> Res.drawable.ic_project_cocktail
            "pizza", "food", "dinner" -> Res.drawable.ic_project_pizza
            "pie", "dessert" -> Res.drawable.ic_project_pie
            "ice_cube", "cold" -> Res.drawable.ic_project_ice_cube

            // 🎉 Eventos y Regalos
            "cake", "birthday" -> Res.drawable.ic_project_cake
            "gift", "present" -> Res.drawable.ic_project_gift
            "confetti", "party" -> Res.drawable.ic_project_confetti
            "banner", "celebration" -> Res.drawable.ic_project_banner

            // 🏥 Salud y Farmacia
            "bandaids", "health" -> Res.drawable.ic_project_bandaids
            "pills", "pharmacy" -> Res.drawable.ic_project_pills
            "syringe", "hospital" -> Res.drawable.ic_project_syringe
            "cross", "medical" -> Res.drawable.ic_project_cross

            // 🏢 Gastos Generales / Casa
            "papers", "bills", "rent" -> Res.drawable.ic_project_papers
            "water_drop", "water", "utilities" -> Res.drawable.ic_project_water_drop
            "cloud" -> Res.drawable.ic_project_cloud

            // 👥 Por defecto (Si viene un tipo raro o "general")
            else -> Res.drawable.ic_project_two_people
        }
}

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

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class AuthResponse(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: String,
    val user: UserData
)

@Serializable
data class UserData(
    val id: String,
    val email: String? = null
)