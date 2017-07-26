package com.costular.randomco.data.source.remote

import com.costular.randomco.data.UserListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

/**
 * Created by costular on 14/07/17.
 */
interface UsersAPIService {

    @GET("/")
    fun getUsers(@Query("results") quantity: Int = 10) : Call<UserListResponse>

    companion object {

        fun create(): UsersAPIService {
            val httpClient: OkHttpClient = OkHttpClient
                    .Builder()
                    .addInterceptor(HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BASIC))
                    .build()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .baseUrl("https://api.randomuser.me/")
                    .build()

            return retrofit.create<UsersAPIService>(UsersAPIService::class.java)
        }
    }
}