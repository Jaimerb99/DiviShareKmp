package com.jrb.divishare.`data`.datasource.local.db.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.EntityUpsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.jrb.divishare.`data`.datasource.local.db.entity.GroupEntity
import javax.`annotation`.processing.Generated
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
public class GroupDao_Impl(
  __db: RoomDatabase,
) : GroupDao {
  private val __db: RoomDatabase

  private val __upsertAdapterOfGroupEntity: EntityUpsertAdapter<GroupEntity>
  init {
    this.__db = __db
    this.__upsertAdapterOfGroupEntity = EntityUpsertAdapter<GroupEntity>(object : EntityInsertAdapter<GroupEntity>() {
      protected override fun createQuery(): String = "INSERT INTO `group_table` (`id`,`name`,`currency`,`inviteCode`,`createdAt`) VALUES (?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: GroupEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.currency)
        val _tmpInviteCode: String? = entity.inviteCode
        if (_tmpInviteCode == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpInviteCode)
        }
        val _tmpCreatedAt: String? = entity.createdAt
        if (_tmpCreatedAt == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpCreatedAt)
        }
      }
    }, object : EntityDeleteOrUpdateAdapter<GroupEntity>() {
      protected override fun createQuery(): String = "UPDATE `group_table` SET `id` = ?,`name` = ?,`currency` = ?,`inviteCode` = ?,`createdAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: GroupEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.currency)
        val _tmpInviteCode: String? = entity.inviteCode
        if (_tmpInviteCode == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpInviteCode)
        }
        val _tmpCreatedAt: String? = entity.createdAt
        if (_tmpCreatedAt == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpCreatedAt)
        }
        statement.bindLong(6, entity.id.toLong())
      }
    })
  }

  public override suspend fun upsertGroups(groups: List<GroupEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __upsertAdapterOfGroupEntity.upsert(_connection, groups)
  }

  public override fun getAllGroups(): Flow<List<GroupEntity>> {
    val _sql: String = "SELECT * FROM group_table ORDER BY id DESC"
    return createFlow(__db, false, arrayOf("group_table")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfCurrency: Int = getColumnIndexOrThrow(_stmt, "currency")
        val _columnIndexOfInviteCode: Int = getColumnIndexOrThrow(_stmt, "inviteCode")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _result: MutableList<GroupEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: GroupEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpCurrency: String
          _tmpCurrency = _stmt.getText(_columnIndexOfCurrency)
          val _tmpInviteCode: String?
          if (_stmt.isNull(_columnIndexOfInviteCode)) {
            _tmpInviteCode = null
          } else {
            _tmpInviteCode = _stmt.getText(_columnIndexOfInviteCode)
          }
          val _tmpCreatedAt: String?
          if (_stmt.isNull(_columnIndexOfCreatedAt)) {
            _tmpCreatedAt = null
          } else {
            _tmpCreatedAt = _stmt.getText(_columnIndexOfCreatedAt)
          }
          _item = GroupEntity(_tmpId,_tmpName,_tmpCurrency,_tmpInviteCode,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAll() {
    val _sql: String = "DELETE FROM group_table"
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
