package com.costular.randomco.utils.injection

import android.content.Context
import com.costular.randomco.BuildConfig
import com.costular.randomco.RandomcoApp
import com.costular.randomco.data.source.UsersRepository
import com.costular.randomco.data.source.local.LocalUserDataSource
import com.costular.randomco.data.source.remote.RemoteUserDataSource
import com.costular.randomco.data.source.remote.UsersAPIService

/**
 * Created by costular on 15/07/17.
 */
object Injection {

    fun providesUsersRepository(context: Context):
            UsersRepository =
            UsersRepository(providesLocalUserDataSource(context),
                    providesRemoteUserDataSource(providesUsersApiService()))

    fun providesLocalUserDataSource(context: Context) = LocalUserDataSource(context)

    fun providesRemoteUserDataSource(apiService: UsersAPIService) = RemoteUserDataSource(apiService)

    fun providesUsersApiService(): UsersAPIService =
            UsersAPIService.create()

}