package com.costular.randomco.data.source.remote

import com.costular.randomco.data.User
import com.costular.randomco.data.UserListResponse
import com.costular.randomco.data.source.UserDataSource
import com.costular.randomco.utils.extensions.enqueue

/**
 * Created by costular on 14/07/17.
 */
class RemoteUserDataSource(val usersAPIService: UsersAPIService) : UserDataSource {

    override fun getUsers(success: (List<User>) -> Unit, error: (String) -> Unit) {
        usersAPIService.getUsers()
                .enqueue(
                        { success(it.body()!!.results) },
                        { error(it) }
                )
    }

    override fun getUser(email: String, success: (User) -> Unit, error: (String) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteUser(email: String, success: () -> Unit, error: (String) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun favoriteUser(email: String, toFavorite: Boolean, success: () -> Unit, error: (String) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}