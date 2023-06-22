package ru.unclediga.dao;


import ru.unclediga.models.*
import org.jetbrains.exposed.sql.*;
import org.jetbrains.exposed.sql.transactions.*;
// import org.jetbrains.exposed.sql.transactions.experimental.*;
// import kotlinx.coroutines.*;

object DatabaseFactory {
    fun init(){
        val driverClassName = "org.h2.Driver"
        val jdbcUrl = "jdbc:h2:file:./target/db"
        val database = Database.connect(jdbcUrl, driverClassName)

        transaction(database){
            SchemaUtils.create(Articles)    
        }
    }     
}


