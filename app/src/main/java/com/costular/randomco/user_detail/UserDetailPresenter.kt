package com.costular.randomco.user_detail

import com.costular.randomco.data.User
import com.costular.randomco.data.source.UsersRepository

/**
 * Created by costular on 15/07/17.
 */
class UserDetailPresenter(val view: UserDetailContract.View,
                          val repository: UsersRepository) : UserDetailContract.Presenter {

    init {
        view.setPresenter(this)
    }

    override fun loadUser(userEmail: String) {
        view.showLoading(true)

        repository.getUser(
                userEmail,
                {
                    user ->
                    view.showUser(user)
                    view.showLoading(false)
                },
                {
                    error ->
                    view.showError(error)
                    view.showLoading(false)
                }
        )
    }

}