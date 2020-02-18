package com.example.reactivekotlindemo.config

import io.vertx.pgclient.PgConnectOptions
import io.vertx.pgclient.PgPool
import io.vertx.sqlclient.PoolOptions
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "db")
class PgClientConfig {
    lateinit var port: String
    lateinit var user: String
    lateinit var password: String
    lateinit var host: String

    @Bean
    fun pgPool(): PgPool {
        val connectOptions = PgConnectOptions()
            .setPort(port.toInt())
            .setHost(host)
            .setDatabase("demo")
            .setUser(user)
            .setPassword(password)

        val poolOptions = PoolOptions().setMaxSize(5)

        return PgPool.pool(connectOptions, poolOptions)
    }
}