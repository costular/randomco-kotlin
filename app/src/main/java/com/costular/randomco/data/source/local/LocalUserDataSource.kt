package com.costular.randomco.data.source.local

import android.content.Context
import com.costular.randomco.data.User
import com.costular.randomco.data.source.UserDataSource
import org.jetbrains.anko.db.RowParser
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import javax.inject.Inject
import javax.sql.DataSource

/**
 * Created by costular on 14/07/17.
 */
class LocalUserDataSource @Inject constructor(val context: Context) : UserDataSource {

    val db by lazy { UsersDbHelper(context) }
    val rowParser = classParser<User>()

    override suspend fun getUsers(): List<User> {
        return db.use {
            select(Table_User.TABLE_NAME)
                    .parseList(rowParser)
        }
    }

    override suspend fun getUser(): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}