package com.example.reactivekotlindemo

import com.example.reactivekotlindemo.config.PgClientConfig
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveKotlinDemoApplication

fun main(args: Array<String>) {
	val app = runApplication<ReactiveKotlinDemoApplication>(*args)

	val clientConfig = app.getBean(PgClientConfig::class.java)

	val config = HikariConfig()
	config.username = clientConfig.user
	config.password = clientConfig.password
	config.driverClassName = "org.postgresql.Driver"
	config.jdbcUrl = "jdbc:postgresql://${clientConfig.host}:${clientConfig.port}/demo"
	config.maximumPoolSize = 1

	HikariDataSource(config).use { dataSource ->
		Flyway.configure()
			.dataSource(dataSource)
			.schemas("demo")
			.load()
			.migrate()
	}
}
