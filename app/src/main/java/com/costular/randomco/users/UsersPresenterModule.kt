package com.costular.randomco.users

import dagger.Module
import dagger.Provides

/**
 * Created by costular on 14/07/17.
 */
@Module
class UsersPresenterModule(val view: UsersContract.View) {

    @Provides
    fun providesUsersContractView(): UsersContract.View = view

}