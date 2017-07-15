package com.costular.randomco.users

import com.costular.randomco.data.User
import com.costular.randomco.data.source.UsersRepository
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.uiThread
import javax.inject.Inject

/**
 * Created by costular on 14/07/17.
 */
class UsersPresenter @Inject constructor(val view: UsersContract.View,
                                         val repository: UsersRepository) : UsersContract.Presenter {

    private var firstRun: Boolean = true

    override fun load(forceUpdate: Boolean) {
        view.setLoading(true)

        // Check if we need to force update
        if(forceUpdate || firstRun) repository.forceToUpdate()
        firstRun = false

        // call repository through a coroutine
        async(UI) {
            val result = repository.getUsers()
            view.showUsers(result)
            view.setLoading(false)
        }

    }

    @Inject
    fun loadPresenter() {
        view.setPresenter(this)
    }

    override fun loadMore() {

    }

    override fun openDetailUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun favoriteUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}