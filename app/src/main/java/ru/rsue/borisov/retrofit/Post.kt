package ru.rsue.borisov.retrofit

import com.google.gson.annotations.SerializedName

class Post(_userId: Int, _title: String?, _text: String) {
    private var userId = _userId
    private val id = 0
    private var title: String? = _title

    @SerializedName("body")
    var text: String? = _text

    fun getUserId(): Int {
        return userId
    }

    @JvmName("getId1")
    fun getId(): Int {
        return id
    }

    @JvmName("getTitle1")
    fun getTitle(): String? {
        return title
    }
}
