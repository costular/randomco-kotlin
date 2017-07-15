package com.costular.randomco

import android.app.Application
import com.costular.randomco.data.source.DaggerUserRepositoryComponent
import com.costular.randomco.data.source.UserRepositoryComponent
import com.costular.randomco.utils.di.HttpModule

/**
 * Created by costular on 14/07/17.
 */
class RandomcoApp : Application() {

    companion object {
        val BASE_API_URL = "https://randomuser.me/api/"
    }

    val repositoryComponent: UserRepositoryComponent by lazy {
        DaggerUserRepositoryComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .httpModule(HttpModule())
                .build()
    }
}