package com.costular.randomco.users

import com.costular.randomco.data.User
import com.costular.randomco.data.UserSort
import com.costular.randomco.data.source.UsersRepository

/**
 * Created by costular on 14/07/17.
 */
class UsersPresenter(val view: UsersContract.View,
                     val repository: UsersRepository,
                     val favoriteUsers: Boolean = false) : UsersContract.Presenter {

    private var sort: UserSort = UserSort.NAME

    init {
        view.setPresenter(this)
    }

    override fun load(forceUpdate: Boolean, query: String) {
        view.setLoading(true)

        // Check if we need to force update or just get local users
        if (forceUpdate) repository.forceToUpdate()

        // Callback as lambda rocks!
        repository.getUsers({
            users ->
            var finalUsers = if (favoriteUsers) users.filter { it.favorite } else users
            finalUsers = when(sort) {
                UserSort.NAME -> finalUsers.sortedBy { it.name.first }
                UserSort.GENDER -> finalUsers.sortedBy { it.gender }
            }
            finalUsers = if(query.isNotEmpty()) searchUsers(finalUsers, query) else finalUsers

            view.showUsers(finalUsers)
            view.setLoading(false)
        }, {
            error ->
            view.showError(error)
            view.setLoading(false)
        })
    }

    fun searchUsers(users: List<User>, query: String): List<User> {
        return users.filter {
            (it.name.first.contains(query) or (it.name.last).contains(query)) or (it.email.contains(query))
        }
    }

    override fun loadMore() {
        view.setLoading(true)

        // Force update to load more users from API
        repository.forceToUpdate()

        repository.getUsers({
            users ->
            view.appendUsers(users)
            view.setLoading(false)
        }, {
           error ->
            view.showError(error)
            view.setLoading(false)
        })
    }

    override fun openDetailUser(user: User) {
        view.openDetailUser(user.email)
    }

    override fun favoriteUser(user: User) {
        repository.favoriteUser(user.email,
                user.favorite,
                {
                    user.favorite = !user.favorite
                    view.markAsFavorite(user)
                },
                { view.showError(it) })
    }

    override fun deleteUser(user: User) {
        repository.deleteUser(
                user.email,
                { view.removeUser(user) },
                { view.showError(it) })
    }

    override fun setSort(sort: UserSort) {
        this.sort = sort
        load(false)
    }
}