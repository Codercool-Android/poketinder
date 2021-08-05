package com.abeltarazona.poketinder.data

import java.io.Serializable

data class User(
    val id: String,
    val name: String,
    val email: String
) : Serializable {
    fun getImage() = "https://graph.facebook.com/$id/picture?type=large&width=720&height=720"
}

// Serializable o Parcelable