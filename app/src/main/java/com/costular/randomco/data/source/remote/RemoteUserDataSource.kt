package com.costular.randomco.data.source.remote

import android.content.Context
import com.costular.randomco.data.User
import com.costular.randomco.data.source.UserDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResponse
import ru.gildor.coroutines.retrofit.awaitResult
import javax.inject.Inject

/**
 * Created by costular on 14/07/17.
 */
class RemoteUserDataSource @Inject constructor(val usersAPIService: UsersAPIService) : UserDataSource {

    override suspend fun getUsers(): List<User> {
        val response: Response<List<User>> = usersAPIService.getUsers().awaitResponse()
        return response.body()!!
    }

    override suspend fun getUser(): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}