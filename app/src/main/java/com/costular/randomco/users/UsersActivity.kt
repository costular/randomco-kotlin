package com.costular.randomco.users

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

import com.costular.randomco.R
import com.costular.randomco.RandomcoApp
import javax.inject.Inject

class UsersActivity : AppCompatActivity() {

    @Inject
    lateinit var userPresenter: UsersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener(View.OnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        })

        // Create and load the fragment
        val fragment: UsersFragment = UsersFragment()
        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()

        // Create the presenter
        DaggerUsersComponent
                .builder()
                .userRepositoryComponent((application as RandomcoApp).repositoryComponent)
                .usersPresenterModule(UsersPresenterModule(fragment))
                .build()
                .inject(this)
    }

}
