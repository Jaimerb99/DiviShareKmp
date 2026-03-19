package com.jrb.divishare.data.datasource.local.db

import androidx.room.RoomDatabaseConstructor

expect object DbFactory : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}