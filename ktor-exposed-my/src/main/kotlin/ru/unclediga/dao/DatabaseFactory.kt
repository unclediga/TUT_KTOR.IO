package ru.unclediga.dao;


import kotlinx.coroutines.Dispatchers
import ru.unclediga.models.*
import org.jetbrains.exposed.sql.*;
import org.jetbrains.exposed.sql.transactions.*;
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.io.File
import io.ktor.server.config.ApplicationConfig

import com.zaxxer.hikari.*
// import org.jetbrains.exposed.sql.transactions.experimental.*;
// import kotlinx.coroutines.*;

object DatabaseFactory {

    fun init(config: ApplicationConfig) {
        val driverClassName = config.property("storage.driverClassName").getString()
        val jdbcUrl = config.property("storage.jdbcUrl").getString() +
            config.propertyOrNull("storage.dbFilePath")?.getString()?.let {
          	File(it).canonicalFile.absolutePath
            } ?: ""  

        val database = Database.connect(createHikariDataSource(url = jdbcUrl, driver = driverClassName))

        transaction(database) {
            SchemaUtils.create(Articles)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

   private fun createHikariDataSource(url: String, driver: String) = HikariDataSource(
   	HikariConfig().apply {
		driverClassName = driver
	        jdbcUrl = url
        	maximumPoolSize = 3
	        isAutoCommit = false
	        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
	        validate()
	})   
      
   
    
}


