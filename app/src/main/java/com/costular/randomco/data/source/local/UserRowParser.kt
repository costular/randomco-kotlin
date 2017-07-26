package com.costular.randomco.data.source.local

import com.costular.randomco.data.User
import org.jetbrains.anko.db.MapRowParser

/**
 * Created by costular on 15/07/17.
 */
class UserRowParser : MapRowParser<User> {

    override fun parseRow(columns: Map<String, Any?>): User {
        val email = columns.getValue(Table_User.EMAIL)
        val cell = columns.getValue(Table_User.CELL)
        val phone = columns.getValue(Table_User.PHONE)
        val registered = columns.getValue(Table_User.REGISTERED)
        val city = columns.getValue(Table_User.LOCATION_CITY)
        val street = columns.getValue(Table_User.LOCATION_STREET)
        val state = columns.getValue(Table_User.LOCATION_STATE)
        val firstName = columns.getValue(Table_User.NAME_FIRST)
        val lastName = columns.getValue(Table_User.NAME_LAST)
        val thumbnailPicture = columns.getValue(Table_User.PICTURE_THUMBNAIL)
        val mediumPicture = columns.getValue(Table_User.PICTURE_MEDIUM)
        val largePicture = columns.getValue(Table_User.PICTURE_LARGE)
        val gender = columns.getValue(Table_User.GENDER)
        val favorite = columns.getValue(Table_User.FAVORITE).toString() == "1"

        return User(gender.toString(), firstName.toString(), lastName.toString(), street.toString(),
                city.toString(), state.toString(), email.toString(), phone.toString(), cell.toString(),
                registered.toString(), largePicture.toString(), mediumPicture.toString(), thumbnailPicture.toString(), favorite)
    }

}