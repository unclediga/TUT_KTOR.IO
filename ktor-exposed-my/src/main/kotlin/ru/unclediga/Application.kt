package ru.unclediga

import io.ktor.server.application.*
import ru.unclediga.plugins.*
import ru.unclediga.dao.DatabaseFactory

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    DatabaseFactory.init() 
    configureRouting()
}