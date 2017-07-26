package com.costular.randomco.user_detail

import com.costular.randomco.BaseView
import com.costular.randomco.data.User

/**
 * Created by costular on 14/07/17.
 */
interface UserDetailContract {

    interface View : BaseView<Presenter> {

        fun showLoading(isVisible: Boolean)

        fun showUser(user: User)

        fun showError(error: String)
    }

    interface Presenter {

        fun loadUser(userEmail: String)
    }
}