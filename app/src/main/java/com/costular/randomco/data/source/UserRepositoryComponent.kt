package com.costular.randomco.data.source

import com.costular.randomco.ApplicationModule
import com.costular.randomco.utils.di.HttpModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by costular on 14/07/17.
 */
@Singleton
@Component(modules = arrayOf(HttpModule::class, UserRepositoryModule::class, ApplicationModule::class))
interface UserRepositoryComponent {

    fun getUsersRepository(): UsersRepository
}