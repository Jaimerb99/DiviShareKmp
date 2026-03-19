package com.jrb.divishare.`data`.datasource.local.db

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.jrb.divishare.`data`.datasource.local.db.dao.ExpenseDao
import com.jrb.divishare.`data`.datasource.local.db.dao.ExpenseDao_Impl
import com.jrb.divishare.`data`.datasource.local.db.dao.GroupDao
import com.jrb.divishare.`data`.datasource.local.db.dao.GroupDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _groupDao: Lazy<GroupDao> = lazy {
    GroupDao_Impl(this)
  }

  private val _expenseDao: Lazy<ExpenseDao> = lazy {
    ExpenseDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1, "f7a049992b23c7e144bcc3eac8339bf7", "5d4b3f5f29576775ede1cdcf78b6199d") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `group_table` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `currency` TEXT NOT NULL, `inviteCode` TEXT, `createdAt` TEXT, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `profile_table` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `email` TEXT, `phone` TEXT, `avatarUrl` TEXT, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `expense_table` (`id` INTEGER NOT NULL, `groupId` INTEGER NOT NULL, `paidBy` INTEGER NOT NULL, `description` TEXT NOT NULL, `category` TEXT NOT NULL, `totalAmount` REAL NOT NULL, `date` TEXT, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `expense_split_table` (`expenseId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, `amountOwed` REAL NOT NULL, PRIMARY KEY(`expenseId`, `userId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f7a049992b23c7e144bcc3eac8339bf7')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `group_table`")
        connection.execSQL("DROP TABLE IF EXISTS `profile_table`")
        connection.execSQL("DROP TABLE IF EXISTS `expense_table`")
        connection.execSQL("DROP TABLE IF EXISTS `expense_split_table`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsGroupTable: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsGroupTable.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGroupTable.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGroupTable.put("currency", TableInfo.Column("currency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGroupTable.put("inviteCode", TableInfo.Column("inviteCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGroupTable.put("createdAt", TableInfo.Column("createdAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysGroupTable: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesGroupTable: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoGroupTable: TableInfo = TableInfo("group_table", _columnsGroupTable, _foreignKeysGroupTable, _indicesGroupTable)
        val _existingGroupTable: TableInfo = read(connection, "group_table")
        if (!_infoGroupTable.equals(_existingGroupTable)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |group_table(com.jrb.divishare.data.datasource.local.db.entity.GroupEntity).
              | Expected:
              |""".trimMargin() + _infoGroupTable + """
              |
              | Found:
              |""".trimMargin() + _existingGroupTable)
        }
        val _columnsProfileTable: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsProfileTable.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProfileTable.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProfileTable.put("email", TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProfileTable.put("phone", TableInfo.Column("phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProfileTable.put("avatarUrl", TableInfo.Column("avatarUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysProfileTable: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesProfileTable: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoProfileTable: TableInfo = TableInfo("profile_table", _columnsProfileTable, _foreignKeysProfileTable, _indicesProfileTable)
        val _existingProfileTable: TableInfo = read(connection, "profile_table")
        if (!_infoProfileTable.equals(_existingProfileTable)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |profile_table(com.jrb.divishare.data.datasource.local.db.entity.ProfileEntity).
              | Expected:
              |""".trimMargin() + _infoProfileTable + """
              |
              | Found:
              |""".trimMargin() + _existingProfileTable)
        }
        val _columnsExpenseTable: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsExpenseTable.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenseTable.put("groupId", TableInfo.Column("groupId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenseTable.put("paidBy", TableInfo.Column("paidBy", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenseTable.put("description", TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenseTable.put("category", TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenseTable.put("totalAmount", TableInfo.Column("totalAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenseTable.put("date", TableInfo.Column("date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysExpenseTable: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesExpenseTable: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoExpenseTable: TableInfo = TableInfo("expense_table", _columnsExpenseTable, _foreignKeysExpenseTable, _indicesExpenseTable)
        val _existingExpenseTable: TableInfo = read(connection, "expense_table")
        if (!_infoExpenseTable.equals(_existingExpenseTable)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |expense_table(com.jrb.divishare.data.datasource.local.db.entity.ExpenseEntity).
              | Expected:
              |""".trimMargin() + _infoExpenseTable + """
              |
              | Found:
              |""".trimMargin() + _existingExpenseTable)
        }
        val _columnsExpenseSplitTable: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsExpenseSplitTable.put("expenseId", TableInfo.Column("expenseId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenseSplitTable.put("userId", TableInfo.Column("userId", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenseSplitTable.put("amountOwed", TableInfo.Column("amountOwed", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysExpenseSplitTable: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesExpenseSplitTable: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoExpenseSplitTable: TableInfo = TableInfo("expense_split_table", _columnsExpenseSplitTable, _foreignKeysExpenseSplitTable, _indicesExpenseSplitTable)
        val _existingExpenseSplitTable: TableInfo = read(connection, "expense_split_table")
        if (!_infoExpenseSplitTable.equals(_existingExpenseSplitTable)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |expense_split_table(com.jrb.divishare.data.datasource.local.db.entity.ExpenseSplitEntity).
              | Expected:
              |""".trimMargin() + _infoExpenseSplitTable + """
              |
              | Found:
              |""".trimMargin() + _existingExpenseSplitTable)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "group_table", "profile_table", "expense_table", "expense_split_table")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(GroupDao::class, GroupDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ExpenseDao::class, ExpenseDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun groupDao(): GroupDao = _groupDao.value

  public override fun expenseDao(): ExpenseDao = _expenseDao.value
}
