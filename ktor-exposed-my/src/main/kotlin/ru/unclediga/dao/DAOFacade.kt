package ru.unclediga.dao

import ru.unclediga.models.Article

interface DAOFacade {
    suspend fun allArticles(): List<Article>
    suspend fun article(id: Int): Article?
    suspend fun newArticle(title: String, body: String): Article?
    suspend fun editArticle(id: Int, title: String, body: String): Boolean
    suspend fun deleteArticle(id: Int): Boolean
}