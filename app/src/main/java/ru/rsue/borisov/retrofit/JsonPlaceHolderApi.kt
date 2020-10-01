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

    @POST("posts")
    fun createPost(@Body post: Post): Call<Post>


    //3
    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") text: String
    ): Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(@FieldMap fields: Map<String, String>): Call<Post>


    //4
    @PUT("posts/{id}")
    fun putPost(@Path("id") id: Int, @Body post: Post): Call<Post>

    @PATCH("posts/{id}")
    fun patchPost(@Path("id") id: Int, @Body post: Post): Call<Post>
}