package ru.unclediga.example.models

import java.util.concurrent.atomic.AtomicInteger

class Article
private constructor(val id: Int, var title: String, var body: String) {
    companion object {
        private val idCounter = AtomicInteger()

        fun newEntry(title: String, body: String) = Article(idCounter.getAndIncrement(), title, body)
    }
}


val articles = mutableListOf(
    Article.newEntry(
        "The drive to develop!",
        "...it's what keeps me going."
    ),
    Article.newEntry("My article 1", "Java super!"),
    Article.newEntry("My article 2", "Kotlin is not bad!")
)