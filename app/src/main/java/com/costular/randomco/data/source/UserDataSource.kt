package com.costular.randomco.data.source

import com.costular.randomco.data.User

/**
 * Created by costular on 14/07/17.
 */
interface UserDataSource {

    fun getUsers(success: (List<User>) -> Unit, error: (String) -> Unit)

    fun getUser(email: String, success: (User) -> Unit, error: (String) -> Unit)

    fun deleteUser(email: String, success: () -> Unit, error: (String) -> Unit)

    fun favoriteUser(email: String, isFavorite: Boolean, success: () -> Unit, error: (String) -> Unit)

}