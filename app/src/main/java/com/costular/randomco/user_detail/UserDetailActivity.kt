package com.costular.randomco.user_detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.costular.randomco.R
import com.costular.randomco.utils.BaseActivity
import com.costular.randomco.utils.injection.Injection

class UserDetailActivity : BaseActivity() {

    companion object {
        val PARAM_EMAIL = "param_email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        setupToolbar()
        setToolbarTransparent()

        val email = intent.getStringExtra(PARAM_EMAIL)

        val fragment: UserDetailFragment = UserDetailFragment.create(email)
        UserDetailPresenter(fragment, Injection.providesUsersRepository(this))

        fragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
    }


}
