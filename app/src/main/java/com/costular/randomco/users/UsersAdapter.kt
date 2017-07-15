package com.costular.randomco.users

import android.view.View
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.costular.randomco.R
import com.costular.randomco.data.User
import com.costular.randomco.utils.extensions.inflate
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

/**
 * Created by costular on 14/07/17.
 */
class UsersAdapter(val users: MutableList<User>, val listener: UserActionsListener) :
        RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    interface UserActionsListener {
        fun onUserCellClick(user: User)
        fun onUserAvatarClick(user: User)
        fun onUserFavoriteClick(user: User)
    }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
    UserViewHolder(parent.inflate(R.layout.row_item_user), listener)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindAndParseUser(users[position])
    }

    fun addUsersAndNotify(users: List<User>) {
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    class UserViewHolder(val view: View, val listener: UserActionsListener) :
            RecyclerView.ViewHolder(view) {

        private val userAvatar = view.find<ImageView>(R.id.user_avatar)
        private val userName = view.find<TextView>(R.id.user_name)
        private val userEmail = view.find<TextView>(R.id.user_email)
        private val userPhone = view.find<TextView>(R.id.user_phone)
        private val userActionFavorite = view.find<ImageButton>(R.id.user_action_favorite)

        /**
         * Bind and parse viewholder views against User object
         */
        fun bindAndParseUser(user: User) {
            with(user) {
                Picasso.with(view.context)
                        .load(user.picture.thumbnail)
                        .centerCrop()
                        .into(userAvatar)

                userName.text = user.name
                userEmail.text = user.email
                userPhone.text = user.phone

                view.setOnClickListener { listener.onUserCellClick(this) }
                userAvatar.setOnClickListener { listener.onUserAvatarClick(this) }
                userActionFavorite.setOnClickListener { listener.onUserFavoriteClick(this) }
            }
        }

    }
}