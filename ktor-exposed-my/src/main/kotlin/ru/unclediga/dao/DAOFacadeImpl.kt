package ru.unclediga.dao

import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import ru.unclediga.dao.DatabaseFactory.dbQuery
import ru.unclediga.models.Article
import ru.unclediga.models.Articles

class DAOFacadeImpl : DAOFacade {

    private fun resultRowToArticle(row: ResultRow) =
        Article(
            row[Articles.id],
            row[Articles.title],
            row[Articles.body],
        )

    override suspend fun allArticles(): List<Article> =
        dbQuery {
            Articles
                .selectAll()
                .map(::resultRowToArticle)
        }


    override suspend fun article(id: Int): Article? =
        dbQuery {
            Articles
                .select(Articles.id eq id)
                .map(::resultRowToArticle)
                .singleOrNull()
        }


    override suspend fun newArticle(title: String, body: String): Article? = dbQuery {
        val insertStatement = Articles.insert {
            it[Articles.title] = title
            it[Articles.body] = body
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }

    override suspend fun editArticle(id: Int, title: String, body: String): Boolean =
        dbQuery {
            Articles.update({ Articles.id eq id }) {
                it[Articles.title] = title
                it[Articles.body] = body
            } > 0
        }


    override suspend fun deleteArticle(id: Int): Boolean = dbQuery {
        Articles.deleteWhere { Articles.id eq id } > 0
    }
}

val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if (allArticles().isEmpty()) {
            newArticle("The drive to develop!", "...it's what keeps me going.")
        }
    }
}
