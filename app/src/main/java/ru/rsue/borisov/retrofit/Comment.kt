package ru.rsue.borisov.retrofit

import com.google.gson.annotations.SerializedName

class Comment {
    val postId = 0
    val id = 0
    val name: String? = null
    val email: String? = null

    @SerializedName("body")
    val text: String? = null
}