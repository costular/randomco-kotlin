package com.costular.randomco.users

import com.costular.randomco.BaseView
import com.costular.randomco.data.User
import com.costular.randomco.data.UserSort

/**
 * Created by costular on 14/07/17.
 */
interface UsersContract {

    interface View : BaseView<Presenter> {

        fun setLoading(isLoading: Boolean)

        fun showError(errorMessage: String)

        fun showUsers(users: List<User>)

        fun appendUsers(users: List<User>)

        fun openDetailUser(email: String)

        fun removeUser(user: User)

        fun markAsFavorite(user: User)
    }

    interface Presenter {

        fun load(forceUpdate: Boolean= false, query: String = "")

        fun loadMore()

        fun setSort(sort: UserSort)

        fun openDetailUser(user: User)

        fun favoriteUser(user: User)

        fun deleteUser(user: User)

    }
}