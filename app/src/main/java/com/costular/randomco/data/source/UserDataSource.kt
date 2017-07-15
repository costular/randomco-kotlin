package com.costular.randomco.data.source

import com.costular.randomco.data.User

/**
 * Created by costular on 14/07/17.
 */
interface UserDataSource {

    suspend fun getUsers(): List<User>

    suspend fun getUser(): User

}