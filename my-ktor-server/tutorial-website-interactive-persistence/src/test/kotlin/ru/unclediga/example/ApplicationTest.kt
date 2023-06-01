package ru.unclediga.example

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import ru.unclediga.example.dao.DatabaseFactory
import ru.unclediga.example.plugins.configureRouting
import kotlin.test.Test
import kotlin.test.assertEquals
import ru.unclediga.example.dao.dao
import kotlin.test.assertTrue

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            DatabaseFactory.init()
            configureRouting()
        }
        client.get("/articles").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertTrue(dao.allArticles().lastIndex > 0, "Нет записей в ARTICLES")
        }
    }
}
