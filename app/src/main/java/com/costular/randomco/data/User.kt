package com.costular.randomco.data

/**
 * Created by costular on 14/07/17.
 */
data class User(val gender: String, val name: Name, val email:String, val phone: String,
                val cell: String, val registered: String, val location: Location,
                val picture: Picture, var favorite: Boolean) {

    fun fullName(): String = "${name.first} ${name.last}"

    constructor(gender: String, firstName: String, lastName: String, street: String, city: String,
                state: String, email: String, phone: String, cell: String, registered: String,
                pictureLarge: String, pictureMedium: String, pictureThumbnail: String, favorite: Boolean) :
            this(gender, Name(firstName, lastName), email, phone, cell, registered,
                    Location(street, city, state), Picture(pictureLarge, pictureMedium, pictureThumbnail), favorite)

}

data class Name(val first: String, val last: String)
data class Picture(val large: String, val medium: String, val thumbnail: String)
data class Location(val street: String, val city: String, val state: String) {

    fun fullLocation(): String = "$street $city, $state"
}