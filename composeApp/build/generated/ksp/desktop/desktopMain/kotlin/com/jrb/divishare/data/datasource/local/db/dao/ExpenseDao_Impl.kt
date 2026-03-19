package com.jrb.divishare.`data`.datasource.local.db.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.jrb.divishare.`data`.datasource.local.db.entity.ExpenseEntity
import com.jrb.divishare.`data`.datasource.local.db.entity.ExpenseSplitEntity
import javax.`annotation`.processing.Generated
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ExpenseDao_Impl(
  __db: RoomDatabase,
) : ExpenseDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfExpenseEntity: EntityUpsertAdapter<ExpenseEntity>

  private val __upsertAdapterOfExpenseSplitEntity: EntityUpsertAdapter<ExpenseSplitEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfExpenseEntity = EntityUpsertAdapter<ExpenseEntity>(object : EntityInsertAdapter<ExpenseEntity>() {
      protected override fun createQuery(): String = "INSERT INTO `expense_table` (`id`,`groupId`,`paidBy`,`description`,`category`,`totalAmount`,`date`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ExpenseEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.groupId.toLong())
        statement.bindLong(3, entity.paidBy.toLong())
        statement.bindText(4, entity.description)
        statement.bindText(5, entity.category)
        statement.bindDouble(6, entity.totalAmount)
        val _tmpDate: String? = entity.date
        if (_tmpDate == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpDate)
        }
      }
    }, object : EntityDeleteOrUpdateAdapter<ExpenseEntity>() {
      protected override fun createQuery(): String = "UPDATE `expense_table` SET `id` = ?,`groupId` = ?,`paidBy` = ?,`description` = ?,`category` = ?,`totalAmount` = ?,`date` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ExpenseEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindLong(2, entity.groupId.toLong())
        statement.bindLong(3, entity.paidBy.toLong())
        statement.bindText(4, entity.description)
        statement.bindText(5, entity.category)
        statement.bindDouble(6, entity.totalAmount)
        val _tmpDate: String? = entity.date
        if (_tmpDate == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpDate)
        }
        statement.bindLong(8, entity.id.toLong())
      }
    })
    this.__upsertAdapterOfExpenseSplitEntity = EntityUpsertAdapter<ExpenseSplitEntity>(object : EntityInsertAdapter<ExpenseSplitEntity>() {
      protected override fun createQuery(): String = "INSERT INTO `expense_split_table` (`expenseId`,`userId`,`amountOwed`) VALUES (?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ExpenseSplitEntity) {
        statement.bindLong(1, entity.expenseId.toLong())
        statement.bindLong(2, entity.userId.toLong())
        statement.bindDouble(3, entity.amountOwed)
      }
    }, object : EntityDeleteOrUpdateAdapter<ExpenseSplitEntity>() {
      protected override fun createQuery(): String = "UPDATE `expense_split_table` SET `expenseId` = ?,`userId` = ?,`amountOwed` = ? WHERE `expenseId` = ? AND `userId` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ExpenseSplitEntity) {
        statement.bindLong(1, entity.expenseId.toLong())
        statement.bindLong(2, entity.userId.toLong())
        statement.bindDouble(3, entity.amountOwed)
        statement.bindLong(4, entity.expenseId.toLong())
        statement.bindLong(5, entity.userId.toLong())
      }
    })
  }

  public override suspend fun upsertExpenses(expenses: List<ExpenseEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfExpenseEntity.upsert(_connection, expenses)
  }

  public override suspend fun upsertExpenseSplits(splits: List<ExpenseSplitEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfExpenseSplitEntity.upsert(_connection, splits)
  }

  public override fun getExpensesForGroup(groupId: Int): Flow<List<ExpenseEntity>> {
    val _sql: String = "SELECT * FROM expense_table WHERE groupId = ? ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("expense_table")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, groupId.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfGroupId: Int = getColumnIndexOrThrow(_stmt, "groupId")
        val _columnIndexOfPaidBy: Int = getColumnIndexOrThrow(_stmt, "paidBy")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfTotalAmount: Int = getColumnIndexOrThrow(_stmt, "totalAmount")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _result: MutableList<ExpenseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ExpenseEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpGroupId: Int
          _tmpGroupId = _stmt.getLong(_columnIndexOfGroupId).toInt()
          val _tmpPaidBy: Int
          _tmpPaidBy = _stmt.getLong(_columnIndexOfPaidBy).toInt()
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          val _tmpTotalAmount: Double
          _tmpTotalAmount = _stmt.getDouble(_columnIndexOfTotalAmount)
          val _tmpDate: String?
          if (_stmt.isNull(_columnIndexOfDate)) {
            _tmpDate = null
          } else {
            _tmpDate = _stmt.getText(_columnIndexOfDate)
          }
          _item = ExpenseEntity(_tmpId,_tmpGroupId,_tmpPaidBy,_tmpDescription,_tmpCategory,_tmpTotalAmount,_tmpDate)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSplitsForExpense(expenseId: Int): List<ExpenseSplitEntity> {
    val _sql: String = "SELECT * FROM expense_split_table WHERE expenseId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, expenseId.toLong())
        val _columnIndexOfExpenseId: Int = getColumnIndexOrThrow(_stmt, "expenseId")
        val _columnIndexOfUserId: Int = getColumnIndexOrThrow(_stmt, "userId")
        val _columnIndexOfAmountOwed: Int = getColumnIndexOrThrow(_stmt, "amountOwed")
        val _result: MutableList<ExpenseSplitEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ExpenseSplitEntity
          val _tmpExpenseId: Int
          _tmpExpenseId = _stmt.getLong(_columnIndexOfExpenseId).toInt()
          val _tmpUserId: Int
          _tmpUserId = _stmt.getLong(_columnIndexOfUserId).toInt()
          val _tmpAmountOwed: Double
          _tmpAmountOwed = _stmt.getDouble(_columnIndexOfAmountOwed)
          _item = ExpenseSplitEntity(_tmpExpenseId,_tmpUserId,_tmpAmountOwed)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAllExpenses() {
    val _sql: String = "DELETE FROM expense_table"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
