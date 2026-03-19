package com.jrb.divishare.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.jrb.divishare.data.datasource.local.db.entity.GroupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Upsert
    suspend fun upsertGroups(groups: List<GroupEntity>)

    @Query("SELECT * FROM group_table ORDER BY id DESC")
    fun getAllGroups(): Flow<List<GroupEntity>>

    @Query("DELETE FROM group_table")
    suspend fun deleteAll()
}