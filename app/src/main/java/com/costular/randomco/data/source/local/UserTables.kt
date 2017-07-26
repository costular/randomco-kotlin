package com.costular.randomco.data.source.local

/**
 * Created by costular on 14/07/17.
 */
object Table_User {
    val TABLE_NAME = "users"
    val GENDER = "gender"
    val NAME_FIRST = "name_first"
    val NAME_LAST = "name_last"
    val LOCATION_STREET = "location_street"
    val LOCATION_CITY = "location_city"
    val LOCATION_STATE = "location_state"
    val EMAIL = "email"
    val PHONE = "phone"
    val CELL = "cell"
    val REGISTERED = "registered"
    val PICTURE_LARGE = "picture_large"
    val PICTURE_MEDIUM = "picture_medium"
    val PICTURE_THUMBNAIL = "picture_thumbnail"
    val FAVORITE = "favorite"
}

object Table_UserDeleted {
    val TABLE_NAME = "users_deleted"
    val EMAIL = "email"
}