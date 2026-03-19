package com.jrb.divishare.`data`.datasource.local.db

import androidx.room.RoomDatabaseConstructor

public actual object DbFactory : RoomDatabaseConstructor<AppDatabase> {
  actual override fun initialize(): AppDatabase = com.jrb.divishare.`data`.datasource.local.db.AppDatabase_Impl()
}
