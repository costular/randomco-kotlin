package com.costular.randomco.data.source.local

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.costular.randomco.data.User
import com.costular.randomco.data.source.UserDataSource
import org.jetbrains.anko.db.*

/**
 * Created by costular on 14/07/17.
 */
class LocalUserDataSource(val context: Context) : UserDataSource {

    val db by lazy { UsersDbHelper(context) }
    val userRowParser: MapRowParser<User> = UserRowParser()

    override fun getUsers(success: (List<User>) -> Unit, error: (String) -> Unit) {
        try {
            val result = db.use {
                select(Table_User.TABLE_NAME)
                        .parseList(userRowParser)
            }
            success(result)
        } catch (exception: Exception) {

        }
    }

    override fun getUser(email: String, success: (User) -> Unit, error: (String) -> Unit) {
        val user = db.use {
            select(Table_User.TABLE_NAME)
                    .whereSimple("${Table_User.EMAIL} = ?", email)
                    .parseSingle(userRowParser)
        }
        success(user)
    }

    override fun deleteUser(email: String, success: () -> Unit, error: (String) -> Unit) {
        db.use {
            delete(Table_User.TABLE_NAME, "email = {email}", "email" to email)
            insert(Table_UserDeleted.TABLE_NAME, Table_UserDeleted.EMAIL to email)
            success()
        }
    }

    override fun favoriteUser(email: String, isFavorite: Boolean, success: () -> Unit, error: (String) -> Unit) {
        val toFavorite = if (isFavorite) 0 else 1

        db.use {
            update(Table_User.TABLE_NAME, Table_User.FAVORITE to toFavorite)
                    .whereSimple("${Table_User.EMAIL} = ?", email)
                    .exec()
            success()
        }
    }

    fun userExists(user: User): Boolean {
        val _user = db.use {
            select(Table_User.TABLE_NAME)
                    .whereSimple("email = ?", user.email)
                    .parseOpt(userRowParser)
        }

        return _user != null
    }

    fun userBlocked(user: User): Boolean {
        val _user = db.use {
            select(Table_User.TABLE_NAME)
                    .whereSimple("email = ?", user.email)
                    .parseOpt(userRowParser)
        }

        return _user != null
    }

    fun insertUsers(users: List<User>) {
        users.forEach { insertUser(it) }
    }

    fun insertUser(user: User) {
        if (userExists(user) or userBlocked(user)) return

        db.use {
            try {
                insert(Table_User.TABLE_NAME,
                        Table_User.CELL to user.cell,
                        Table_User.EMAIL to user.email,
                        Table_User.GENDER to user.gender,
                        Table_User.LOCATION_CITY to user.location.city,
                        Table_User.LOCATION_STATE to user.location.state,
                        Table_User.LOCATION_STREET to user.location.street,
                        Table_User.PHONE to user.phone,
                        Table_User.NAME_FIRST to user.name.first,
                        Table_User.NAME_LAST to user.name.last,
                        Table_User.PICTURE_LARGE to user.picture.large,
                        Table_User.PICTURE_MEDIUM to user.picture.medium,
                        Table_User.PICTURE_THUMBNAIL to user.picture.thumbnail,
                        Table_User.REGISTERED to user.registered)
            } catch (exception: SQLiteConstraintException) {

            }

        }
    }
}