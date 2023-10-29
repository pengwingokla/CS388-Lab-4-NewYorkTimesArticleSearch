package com.example.lab_4

data class ApiResponse(
    val response: Response
)

data class Response(
    val docs: List<Article>
)

data class Article(
    val headline: Headline,
    val pub_date: String,
    val snippet: String,
    val multimedia: List<Multimedia>
)

data class Headline(
    val main: String
)

data class Multimedia(
    val url: String
)

