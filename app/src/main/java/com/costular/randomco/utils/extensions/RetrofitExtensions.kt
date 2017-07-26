package com.costular.randomco.utils.extensions

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by costular on 15/07/17.
 */
fun <T> Call<T>.enqueue(success: (response: Response<T>) -> Unit,
                        failure: (t: String) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            if(response!!.isSuccessful) {
                success(response)
            } else {
                failure(response.message())
            }
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            failure(t!!.message!!)
        }

    })
}