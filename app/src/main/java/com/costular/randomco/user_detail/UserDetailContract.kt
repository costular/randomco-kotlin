package com.costular.randomco.user_detail

import com.costular.randomco.BaseView

/**
 * Created by costular on 14/07/17.
 */
interface UserDetailContract {

    interface View : BaseView<Presenter> {

        fun showLoading(isVisible: Boolean)

        fun loadUser(userEmail: String)

    }

    interface Presenter {

        // nothing here
    }
}