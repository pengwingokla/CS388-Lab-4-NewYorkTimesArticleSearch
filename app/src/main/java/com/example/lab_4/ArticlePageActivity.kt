package com.example.lab_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ArticlePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_page)

        // Retrieve the article details from the Intent
        val heading = intent.getStringExtra("heading")
        val date = intent.getStringExtra("date")
        val subheading = intent.getStringExtra("subheading")

        // Find your TextView elements in your layout and set their text accordingly
        val headingTextView = findViewById<TextView>(R.id.full_article_heading)
        val dateTextView = findViewById<TextView>(R.id.full_article_date)
        val subheadingTextView = findViewById<TextView>(R.id.full_article_text)

        headingTextView.text = heading
        dateTextView.text = date
        subheadingTextView.text = subheading
    }
}