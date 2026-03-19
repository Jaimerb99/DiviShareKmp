package com.jrb.divishare.data.datasource.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jrb.divishare.domain.model.Group

@Entity(tableName = "group_table")
data class GroupEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val currency: String,
    val inviteCode: String?,
    val createdAt: String?
)

fun GroupEntity.toDomain(): Group = Group(
    id = id,
    name = name,
    currency = currency,
    inviteCode = inviteCode,
    createdAt = createdAt
)

fun Group.toEntity(): GroupEntity = GroupEntity(
    id = id,
    name = name,
    currency = currency,
    inviteCode = inviteCode,
    createdAt = createdAt
)