package com.jrb.divishare.data.datasource.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jrb.divishare.domain.model.Profile

@Entity(tableName = "profile_table")
data class ProfileEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String?,
    val phone: String?,
    val avatarUrl: String?
)

fun ProfileEntity.toDomain(): Profile = Profile(
    id = id,
    name = name,
    email = email,
    phone = phone,
    avatarUrl = avatarUrl
)

fun Profile.toEntity(): ProfileEntity = ProfileEntity(
    id = id,
    name = name,
    email = email,
    phone = phone,
    avatarUrl = avatarUrl
)