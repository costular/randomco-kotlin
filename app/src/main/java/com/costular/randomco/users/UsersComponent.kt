package com.costular.randomco.users

import com.costular.randomco.ApplicationModule
import com.costular.randomco.data.source.UserRepositoryComponent
import com.costular.randomco.utils.di.FragmentScope
import com.costular.randomco.utils.di.HttpModule
import dagger.Component

/**
 * Created by costular on 14/07/17.
 */
@FragmentScope
@Component(dependencies = arrayOf(UserRepositoryComponent::class),
        modules = arrayOf(UsersPresenterModule::class, HttpModule::class))
interface UsersComponent {

    fun inject(activity: UsersActivity)
}