package com.costular.randomco.users

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.costular.randomco.R
import com.costular.randomco.data.User
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * Created by costular on 14/07/17.
 */
class UsersFragment : Fragment(), UsersContract.View, UsersAdapter.UserActionsListener {

    lateinit private var presenter: UsersContract.Presenter

    var userAdapter: UsersAdapter? = null

    // Views
    private val userRecycler by lazy { find<RecyclerView>(R.id.users_recyclerview) }
    private val loadingView by lazy { find<ProgressBar>(R.id.loading_view) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_users, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userRecycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun setPresenter(presenter: UsersContract.Presenter) {
        this.presenter = presenter
    }

    override fun onResume() {
        super.onResume()
        presenter.load(false)
    }

    override fun setLoading(isLoading: Boolean) {
        loadingView.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    override fun showError(errorMessage: String) {
        toast(errorMessage)
    }

    override fun showUsers(users: List<User>) {
        if (userAdapter == null) {
            userAdapter = UsersAdapter(users.toMutableList(), this)
            userRecycler.adapter = userAdapter
        } else {
            userAdapter?.addUsersAndNotify(users)
        }
    }

    override fun onUserCellClick(user: User) {
        // It'll be useful in the future
    }

    override fun onUserAvatarClick(user: User) {
        presenter.openDetailUser(user)
    }

    override fun onUserFavoriteClick(user: User) {
        presenter.favoriteUser(user)
    }
}