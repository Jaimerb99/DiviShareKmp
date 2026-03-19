package com.jrb.divishare.data.datasource.local.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.jrb.divishare.data.datasource.local.db.dao.ExpenseDao
import com.jrb.divishare.data.datasource.local.db.dao.GroupDao
import com.jrb.divishare.data.datasource.local.db.entity.ExpenseEntity
import com.jrb.divishare.data.datasource.local.db.entity.ExpenseSplitEntity
import com.jrb.divishare.data.datasource.local.db.entity.GroupEntity
import com.jrb.divishare.data.datasource.local.db.entity.ProfileEntity

internal const val dbFileName = "divishare.db"

@Database(
    entities = [
        GroupEntity::class,
        ProfileEntity::class,
        ExpenseEntity::class,
        ExpenseSplitEntity::class
    ],
    version = 1
)
@ConstructedBy(DbFactory::class) // don't use actual(keyword) reference of DbFactory object as it is used with ConstructedBy
abstract class AppDatabase : RoomDatabase() {
    abstract fun groupDao(): GroupDao
    abstract fun expenseDao(): ExpenseDao
}
