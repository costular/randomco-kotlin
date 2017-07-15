package com.costular.randomco

import dagger.Module
import android.content.Context
import dagger.Provides

/**
 * Created by costular on 14/07/17.
 */
@Module
class ApplicationModule(val context: Context) {

    @Provides
    fun providesContext() : Context = context

}