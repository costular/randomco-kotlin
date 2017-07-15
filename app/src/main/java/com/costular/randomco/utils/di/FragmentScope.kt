package com.costular.randomco.utils.di

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Created by costular on 14/07/17.
 */
@Qualifier
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope