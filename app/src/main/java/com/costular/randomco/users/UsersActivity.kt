package com.costular.randomco.users

import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import com.costular.randomco.R
import com.costular.randomco.data.UserSort
import com.costular.randomco.utils.BaseActivity
import com.costular.randomco.utils.injection.Injection

class UsersActivity : BaseActivity() {

    var presenter: MutableList<UsersPresenter> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        setupToolbar()

        // Check if is a tablet or a phone
        val isTablet = resources.getBoolean(R.bool.is_tablet)
        if (isTablet) {
            loadTabletLayout()
        } else {
            loadPhoneLayout()
        }
    }

    private fun loadPhoneLayout() {
        // Create and load the fragment
        val fragment: UsersFragment = UsersFragment()
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()

        // Create the presenter
        presenter.add(UsersPresenter(fragment, Injection.providesUsersRepository(this)))
    }

    private fun loadTabletLayout() {
        val fragment: UsersFragment = UsersFragment()
        val favoriteFragment: UsersFragment = UsersFragment()

        fragmentManager
                .beginTransaction()
                .add(R.id.left_fragment, fragment)
                .add(R.id.right_fragment, favoriteFragment)
                .commit()

        // Create both presenters
        presenter.add(UsersPresenter(fragment, Injection.providesUsersRepository(this)))
        presenter.add(UsersPresenter(favoriteFragment, Injection.providesUsersRepository(this), true))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_users, menu)

        // Associate searchable configuration with the SearchView
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.forEach { it.load(query=newText!!) }
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_sort -> showOrder()
            R.id.action_search -> {
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun showOrder() {
        PopupMenu(this, findViewById(R.id.action_sort)).apply {
            menuInflater.inflate(R.menu.user_sort, menu)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.ic_sort_gender -> { presenter.forEach { it.setSort(UserSort.GENDER) } }
                    R.id.ic_sort_name -> { presenter.forEach { it.setSort(UserSort.NAME) } }
                }
                true
            }
            show()
        }
    }
}
