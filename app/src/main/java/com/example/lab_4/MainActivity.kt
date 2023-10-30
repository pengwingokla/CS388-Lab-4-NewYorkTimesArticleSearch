package com.example.lab_4

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), ArticleAdapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.ArticleRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create a Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/search/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of the ApiService
        val apiService = retrofit.create(ApiService::class.java)

        val apiKey = "pxKzrnMchPPjkNmIHESMtA8gGWgqTi09"
        val query = "headline"

        // Make the API request
        val call = apiService.getArticles(query, apiKey)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.response?.docs
                    val articleItems = articles?.map {
                        ArticleItem(
                            heading = it.headline.main,
                            date = it.pub_date,
                            subheading = it.snippet,
                            //thumbnail = R.drawable.your_thumbnail_resource // Set the appropriate thumbnail resource
                        )
                    }
                    // Pass to adapter a list of ArticleItem objects
                    val adapter = ArticleAdapter(this@MainActivity, articleItems.orEmpty())
                    adapter.setOnItemClickListener(this@MainActivity)
                    recyclerView.adapter = adapter
                } else {
                    val errorMessage = "API request failed"
                    showToast(errorMessage)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                val errorMessage = "Network request failed"
                showToast(errorMessage)
            }
        })
    }

    override fun onItemClick(article: ArticleItem) {
        // Handle the click event here
        val intent = Intent(this@MainActivity, ArticlePageActivity::class.java)
        // Pass data related to the clicked article to the new activity
        intent.putExtra("heading", article.heading)
        intent.putExtra("date", article.date)
        intent.putExtra("subheading", article.subheading)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}

