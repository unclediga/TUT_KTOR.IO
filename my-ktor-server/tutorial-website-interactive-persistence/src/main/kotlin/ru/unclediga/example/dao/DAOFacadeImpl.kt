package ru.unclediga.example.dao

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.unclediga.example.dao.DatabaseFactory.dbQuery
import ru.unclediga.example.models.Article
import ru.unclediga.example.models.Articles
class DAOFacadeImpl : DAOFacade {
    private fun resultRowToArticle(ro: ResultRow) = Article(
        id = ro[Articles.id],
        title = ro[Articles.title],
        body = ro[Articles.body]
    )

    override suspend fun allArticles(): List<Article> = dbQuery {
        Articles.selectAll().map(::resultRowToArticle)
    }


    override suspend fun article(id: Int): Article? =
        dbQuery {
            Articles
                .select { Articles.id eq id }
                .map(::resultRowToArticle)
                .singleOrNull()
        }


    override suspend fun addNewArticle(title: String, body: String): Article? =
        dbQuery{
            val insertStatement = Articles.insert {
                it[Articles.title] = title
                it[Articles.body] = body
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
        }

    override suspend fun editArticle(id: Int, title: String, body: String) =
        dbQuery {
            Articles.update({ Articles.id eq id }) {
                it[Articles.title] = title
                it[Articles.body] = body
            } > 0
        }

    override suspend fun deleteArticle(id: Int) =
        dbQuery {
            Articles.deleteWhere { Articles.id eq id } > 0
        }
}



val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if (allArticles().isEmpty()) {
            addNewArticle("The drive to develop!", "...it's what keeps me going.")
        }
    }
}
