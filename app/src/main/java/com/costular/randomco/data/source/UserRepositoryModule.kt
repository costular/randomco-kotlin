package com.costular.randomco.data.source

import android.content.Context
import com.costular.randomco.data.source.local.LocalUserDataSource
import com.costular.randomco.data.source.remote.RemoteUserDataSource
import com.costular.randomco.data.source.remote.UsersAPIService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by costular on 14/07/17.
 */
@Module
class UserRepositoryModule {

    @Provides
    @Singleton
    fun providesUsersLocalDataSource(context: Context): LocalUserDataSource = LocalUserDataSource(context)

    @Provides
    @Singleton
    fun providesUsersRemoteDataSource(apiService: UsersAPIService): RemoteUserDataSource =
            RemoteUserDataSource(apiService)

    @Provides
    @Singleton
    fun providesUserRepository(localData: LocalUserDataSource, remoteData: RemoteUserDataSource) :
            UsersRepository = UsersRepository(localData, remoteData)

}