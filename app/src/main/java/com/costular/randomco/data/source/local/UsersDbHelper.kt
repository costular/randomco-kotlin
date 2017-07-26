package com.costular.randomco.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import com.costular.randomco.data.source.local.Table_User as User
import com.costular.randomco.data.source.local.Table_UserDeleted as UserDeleted

/**
 * Created by costular on 14/07/17.
 */
class UsersDbHelper(context: Context) : ManagedSQLiteOpenHelper(context, UsersDbHelper.DB_NAME,
        null, UsersDbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "randomco.db"
        val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(User.TABLE_NAME, true,
                User.EMAIL to TEXT + PRIMARY_KEY,
                User.GENDER to TEXT,
                User.NAME_FIRST to TEXT,
                User.NAME_LAST to TEXT,
                User.LOCATION_STREET to TEXT,
                User.LOCATION_CITY to TEXT,
                User.LOCATION_STATE to TEXT,
                User.PHONE to TEXT,
                User.CELL to TEXT,
                User.REGISTERED to TEXT,
                User.PICTURE_LARGE to TEXT,
                User.PICTURE_MEDIUM to TEXT,
                User.PICTURE_THUMBNAIL to TEXT,
                User.FAVORITE to INTEGER)

        db.createTable(UserDeleted.TABLE_NAME, true,
                UserDeleted.EMAIL to TEXT + PRIMARY_KEY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(User.TABLE_NAME, true)
        db.dropTable(UserDeleted.TABLE_NAME, true)
        onCreate(db)
    }
}