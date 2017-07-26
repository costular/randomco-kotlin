package com.costular.randomco.data.source

import com.costular.randomco.data.User
import com.costular.randomco.data.source.local.LocalUserDataSource
import com.costular.randomco.data.source.remote.RemoteUserDataSource

/**
 * Created by costular on 14/07/17.
 */
open class UsersRepository (val local: LocalUserDataSource,
                       val remote: RemoteUserDataSource) : UserDataSource {

    override fun getUsers(success: (List<User>) -> Unit, error: (String) -> Unit) {
        findBestDataSource()
                .getUsers({
                    success(it.sortedBy { it.name.first }) // user list sorted by name
                    insertUsers(it)
                }, { error(it) })
    }

    override fun getUser(email: String, success: (User) -> Unit, error: (String) -> Unit) {
        findBestDataSource()
                .getUser(email, { success(it) }, { error(it) })
    }

    override fun deleteUser(email: String, success: () -> Unit, error: (String) -> Unit) {
        findBestDataSource()
                .deleteUser(email, { success() }, { error(it) })
    }

    override fun favoriteUser(email: String, isFavorite: Boolean, success: () -> Unit, error: (String) -> Unit) {
        local.favoriteUser(email, isFavorite, { success() }, { error(it) })
    }

    var needToUpdate: Boolean = false

    fun forceToUpdate() {
        needToUpdate = true
    }

    private fun findBestDataSource(): UserDataSource {
        if (needToUpdate) {
            needToUpdate = false
            return remote
        } else {
            return local
        }
    }

    private fun insertUsers(users: List<User>) {
        local.insertUsers(users)
    }

}