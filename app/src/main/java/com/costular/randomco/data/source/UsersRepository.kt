package com.costular.randomco.data.source

import com.costular.randomco.data.User
import com.costular.randomco.data.source.local.LocalUserDataSource
import com.costular.randomco.data.source.remote.RemoteUserDataSource
import com.costular.randomco.data.source.remote.RemoteUserDataSource_Factory
import javax.inject.Inject

/**
 * Created by costular on 14/07/17.
 */
class UsersRepository @Inject constructor(val local: LocalUserDataSource,
                                          val remote: RemoteUserDataSource) : UserDataSource {

    var needToUpdate: Boolean = false

    override suspend fun getUsers(): List<User> {
        return findBestDataSource()
                .getUsers()
    }

    override suspend fun getUser(): User {
        return findBestDataSource()
                .getUser()
    }

    fun forceToUpdate() {
        needToUpdate = true
    }

    private fun findBestDataSource(): UserDataSource {
        if (needToUpdate) {
            return remote
        }
        return local
    }

}