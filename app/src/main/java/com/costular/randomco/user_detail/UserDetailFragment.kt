package com.costular.randomco.user_detail

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.costular.randomco.R
import com.costular.randomco.data.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_detail.*
import org.jetbrains.anko.toast

/**
 * Created by costular on 14/07/17.
 */
class UserDetailFragment : Fragment(), UserDetailContract.View {

    companion object {
        fun create(email: String): UserDetailFragment {
            val fragment: UserDetailFragment = UserDetailFragment()
            val bundle = Bundle()
            bundle.putString(UserDetailActivity.PARAM_EMAIL, email)
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit private var presenter: UserDetailContract.Presenter
    lateinit private var email: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        email = arguments.getString(UserDetailActivity.PARAM_EMAIL, "")
    }

    override fun onResume() {
        super.onResume()
        presenter.loadUser(email)
    }

    override fun showLoading(isVisible: Boolean) {
        loadingView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun showError(error: String) {
        toast(error)
    }


    override fun showUser(user: User) {
        Picasso.with(activity)
                .load(user.picture.large)
                .fit()
                .centerCrop()
                .into(userDetailAvatar)

        userDetailEmail.text = user.email
        userDetailGender.text = user.gender
        userDetailLocation.text = user.location.fullLocation()
        userDetailName.text = user.fullName()
        userDetailRegistered.text = user.registered
    }

    override fun setPresenter(presenter: UserDetailContract.Presenter) {
        this.presenter = presenter
    }

}