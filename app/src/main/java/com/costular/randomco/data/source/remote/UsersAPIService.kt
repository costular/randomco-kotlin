package com.costular.randomco.data.source.remote

import com.costular.randomco.data.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by costular on 14/07/17.
 */
interface UsersAPIService {

    @GET("/")
    fun getUsers(@Query("results") quantity: Int = 30,
                 @Query("page") page: Int = 0) : Call<List<User>>

}