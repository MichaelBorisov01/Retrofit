package ru.rsue.borisov.retrofit

import retrofit2.Call
import retrofit2.http.*


interface JsonPlaceHolderApi {
    @GET("posts")
    fun getPosts(
        @Query("userId") userId: MutableList<Int>,
        @Query("_sort") sort: String?,
        @Query("_order") order: String?
    ): Call<List<Post>>

    @GET("posts")
    fun getPosts(@QueryMap parameters: MutableMap<String, String>): Call<List<Post>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") postId: Int): Call<List<Comment>>

    @GET
    fun getComments(@Url url: String): Call<List<Comment>>
}