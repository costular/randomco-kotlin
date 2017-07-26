package com.costular.randomco.users

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.ProgressBar
import com.costular.randomco.R
import com.costular.randomco.data.User
import com.costular.randomco.user_detail.UserDetailActivity
import com.costular.randomco.utils.PrefManager
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


/**
 * Created by costular on 14/07/17.
 */
class UsersFragment : Fragment(), UsersContract.View, UsersAdapter.UserActionsListener {

    lateinit private var presenter: UsersContract.Presenter

    var userAdapter: UsersAdapter? = null
    var firstRun: Boolean = false

    // Views
    private val userRecycler by lazy { find<RecyclerView>(R.id.users_recyclerview) }
    private val loadingView by lazy { find<ProgressBar>(R.id.loading_view) }
    private val fab by lazy { activity.findViewById(R.id.fab) as FloatingActionButton }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_users, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userRecycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        userRecycler.itemAnimator = DefaultItemAnimator()
        fab.setOnClickListener { presenter.loadMore() }
    }

    override fun setPresenter(presenter: UsersContract.Presenter) {
        this.presenter = presenter
    }

    override fun onResume() {
        super.onResume()
        val prefManager = PrefManager.create(activity)
        firstRun = prefManager.isFirstRun()
        prefManager.setNotFirstRun()

        presenter.load(firstRun)
        firstRun = false
    }

    override fun setLoading(isLoading: Boolean) {
        loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun showError(errorMessage: String) {
        toast(errorMessage)
    }

    override fun showUsers(users: List<User>) {
        userAdapter = UsersAdapter(users.toMutableList(), this)
        userRecycler.adapter = userAdapter
    }

    override fun appendUsers(users: List<User>) {
        userAdapter?.addUsersAndNotify(users)
    }

    override fun removeUser(user: User) {
        userAdapter?.removeUser(user)
    }

    override fun markAsFavorite(user: User) {
        userAdapter?.update(user)
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

    override fun onUserDeleteClick(user: User) {
        presenter.deleteUser(user)
    }

    override fun openDetailUser(email: String) {
        startActivity<UserDetailActivity>(UserDetailActivity.PARAM_EMAIL to email)
    }

}