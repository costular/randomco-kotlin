package com.costular.randomco.users

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.costular.randomco.R
import com.costular.randomco.data.User
import com.costular.randomco.utils.extensions.inflate
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
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
        fun onUserDeleteClick(user: User)
    }

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
    UserViewHolder(parent.inflate(R.layout.row_item_user), listener)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindAndParseUser(users[position])
    }

    fun addUsersAndNotify(users: List<User>) {
        this.users.addAll(users)
        val sorted = this.users.sortedBy { it.name.first }
        this.users.clear()
        this.users.addAll(sorted)
        notifyDataSetChanged()
    }

    fun removeUser(user: User) {
        val index = this.users.indexOf(user)
        this.users.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, this.users.size)
    }

    fun update(user: User) {
        val element = this.users.find { it.email === user.email }
        val index = this.users.indexOf(element)
        this.users[index] = user
        notifyItemChanged(index)
    }

    class UserViewHolder(val view: View, val listener: UserActionsListener) :
            RecyclerView.ViewHolder(view) {

        private val userAvatar = view.find<CircleImageView>(R.id.user_avatar)
        private val userName = view.find<TextView>(R.id.user_name)
        private val userEmail = view.find<TextView>(R.id.user_email)
        private val userPhone = view.find<TextView>(R.id.user_phone)
        private val userActionFavorite = view.find<ImageButton>(R.id.user_action_favorite)
        private val userActionDelete = view.find<ImageButton>(R.id.user_action_delete)

        /**
         * Bind and parse viewholder views against User object
         */
        fun bindAndParseUser(user: User) {
            with(user) {
                Picasso.with(view.context)
                        .load(user.picture.thumbnail)
                        .fit()
                        .centerCrop()
                        .into(userAvatar)

                userName.text = user.fullName()
                userEmail.text = user.email
                userPhone.text = user.phone

                val drawable = getDrawableFav(view.context, user.favorite)
                userActionFavorite.setImageDrawable(drawable)

                view.setOnClickListener { listener.onUserCellClick(this) }
                userAvatar.setOnClickListener { listener.onUserAvatarClick(this) }
                userActionFavorite.setOnClickListener { listener.onUserFavoriteClick(this) }
                userActionDelete.setOnClickListener { listener.onUserDeleteClick(this) }
            }
        }

        private fun getDrawableFav(context: Context, isFavorite: Boolean): Drawable {
            if (isFavorite) {
                return colorToRed(context.getDrawable(R.drawable.ic_favorite_user))
            } else {
                return context.getDrawable(R.drawable.ic_not_favorite)
            }
        }

        private fun colorToRed(drawable: Drawable): Drawable {
            drawable.setTint(Color.RED)
            return drawable
        }
    }
}