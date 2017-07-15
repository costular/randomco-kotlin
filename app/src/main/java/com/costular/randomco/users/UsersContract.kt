package com.costular.randomco.users

import com.costular.randomco.BaseView
import com.costular.randomco.data.User

/**
 * Created by costular on 14/07/17.
 */
interface UsersContract {

    interface View : BaseView<Presenter> {

        fun setLoading(isLoading: Boolean)

        fun showError(errorMessage: String)

        fun showUsers(users: List<User>)
    }

    interface Presenter {

        fun load(forceUpdate: Boolean)

        fun loadMore()

        fun openDetailUser(user: User)

        fun favoriteUser(user: User)

    }
}