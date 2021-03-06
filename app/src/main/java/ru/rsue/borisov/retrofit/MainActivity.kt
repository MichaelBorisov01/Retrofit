package ru.rsue.borisov.retrofit

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private var textViewResult: TextView? = null
    private var jsonPlaceHolderApi: JsonPlaceHolderApi? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewResult = findViewById(R.id.text_view_result)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)
        //getPost()
        //getComments()
        //createPost()
        updatePost()
    }

    private fun getPost() {
        val parameters: MutableMap<String, String> = HashMap()
        parameters["userId"] = "1"
        parameters["_sort"] = "id"
        parameters["_order"] = "desc"

        val call = jsonPlaceHolderApi!!.getPosts(parameters)
        call.enqueue(object : Callback<List<Post>> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    textViewResult!!.text = "Code: " + response.code()
                    return
                }
                val posts = response.body()!!
                for (post in posts) {
                    var content = ""
                    content += """
                               ID: ${post.getId()}
                               
                               """.trimIndent()
                    content += """
                               User ID: ${post.getUserId()}
                               
                               """.trimIndent()
                    content += """
                               Title: ${post.getTitle()}
                               
                               """.trimIndent()
                    content += """
                               Text: ${post.text}
                               
                               """.trimIndent()
                    textViewResult!!.append(content)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                textViewResult!!.text = t.message
            }
        })
    }

    private fun getComments() {
        val call: Call<List<Comment>>? = jsonPlaceHolderApi?.getComments("posts/1/comments")
        call!!.enqueue(object : Callback<List<Comment>> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<Comment>>, response: Response<List<Comment>>
            ) {
                if (!response.isSuccessful) {
                    textViewResult!!.text = "Code: " + response.code()
                    return
                }
                val comments = response.body()!!
                for (comment in comments) {
                    var content = ""
                    content += """
                               ID: ${comment.getId()}
                               
                               """.trimIndent()
                    content += """
                               Post ID: ${comment.getPostId()}
                               
                               """.trimIndent()
                    content += """
                               Name: ${comment.getName()}
                               
                               """.trimIndent()
                    content += """
                               Email: ${comment.getName()}
                               
                               """.trimIndent()
                    content += """
                               Text: ${comment.text}
                               
                               
                               """.trimIndent()
                    textViewResult!!.append(content)
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                textViewResult!!.text = "${t.message}"
            }
        })
    }

    private fun createPost() {
        val post = Post(23, "New Title", "New Text")

        val fields: MutableMap<String, String> = HashMap()
        fields["userId"] = "25"
        fields["title"] = "New Title"
        fields["text"] = "blblbblblbl"

        val call: Call<Post> = jsonPlaceHolderApi!!.createPost(fields)

        call.enqueue(object : Callback<Post> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                if (!response.isSuccessful) {
                    textViewResult!!.text = "Code: " + response.code()
                    return
                }
                val postResponse = response.body()
                var content = ""
                content += """
             Code: ${response.code()}
             
             """.trimIndent()
                content += """
             ID: ${postResponse!!.getId()}
             
             """.trimIndent()
                content += """
             User ID: ${postResponse.getUserId()}
             
             """.trimIndent()
                content += """
             Title: ${postResponse.getTitle().toString()}
             
             """.trimIndent()
                content += """
             Text: ${postResponse.text}
             
             
             """.trimIndent()
                textViewResult!!.text = content
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                textViewResult!!.text = t.message
            }
        })
    }


    private fun updatePost() {
        val post = Post(12, null, "New Text")

        val call: Call<Post> = jsonPlaceHolderApi!!.putPost(5, post)

        call.enqueue(object : Callback<Post> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    textViewResult!!.text = "Code: " + response.code()
                    return
                }
                val postResponse = response.body()
                var content = ""
                content += """
             Code: ${response.code()}
             
             """.trimIndent()
                content += """
             ID: ${postResponse!!.getId()}
             
             """.trimIndent()
                content += """
             User ID: ${postResponse.getUserId()}
             
             """.trimIndent()
                content += """
             Title: ${postResponse.getTitle().toString()}
             
             """.trimIndent()
                content += """
             Text: ${postResponse.text}
             
             
             """.trimIndent()
                textViewResult!!.text = content
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                textViewResult!!.text = t.message
            }
        })
    }
}



