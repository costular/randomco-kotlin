package com.costular.randomco.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by costular on 15/07/17.
 */
class PrefManager(val context: Context) {

    fun getPrefs(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun setNotFirstRun() {
        getPrefs()
                .edit()
                .putBoolean(PARAM_FIRST_RUN, false)
                .apply()
    }

    fun isFirstRun(): Boolean = getPrefs().getBoolean(PARAM_FIRST_RUN, true)

    companion object {
        val PARAM_FIRST_RUN = "first_run"

        fun create(context: Context): PrefManager = PrefManager(context)
    }
}