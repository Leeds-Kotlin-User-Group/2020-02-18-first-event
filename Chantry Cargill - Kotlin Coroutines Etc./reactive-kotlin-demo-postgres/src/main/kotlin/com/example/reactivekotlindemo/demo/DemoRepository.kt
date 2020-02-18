package com.example.reactivekotlindemo.demo

import com.example.reactivekotlindemo.structural.Failable
import io.vertx.kotlin.pgclient.preparedQueryAwait
import io.vertx.kotlin.pgclient.queryAwait
import io.vertx.kotlin.sqlclient.beginAwait
import io.vertx.kotlin.sqlclient.preparedQueryAwait
import io.vertx.pgclient.PgPool
import io.vertx.sqlclient.Transaction
import io.vertx.sqlclient.Tuple
import org.springframework.stereotype.Component

@Component
class DemoRepository(private val pgPool: PgPool) {
    suspend fun findById(id: Int): Demo? {
        return pgPool.preparedQueryAwait("select d.id, d.name from demo.demos d where id = $1", Tuple.of(id))
            .map { row -> Demo(row.getInteger("id"), row.getString("name")) }
            .singleOrNull()
    }

    suspend fun findAll(): List<Demo> {
        return pgPool.queryAwait("select d.id, d.name from demo.demos d")
            .map { row -> Demo(row.getInteger("id"), row.getString("name")) }
    }

    suspend fun save(demo: Demo): Failable<Demo, DemoError> {
        if(demo.id == null) {
            val id = pgPool.preparedQueryAwait(
                "insert into demo.demos (name) values ($1) returning id",
                Tuple.of(demo.name)
            )
                .iterator()
                .next()
                .getInteger("id")

            return Failable.Success(demo.copy(id = id))
        }
        return pgPool.transactionallyAwait {
            val exists = it.preparedQueryAwait("select 1 from demo.demos d where d.id = $1", Tuple.of(demo.id))
                .rowCount() > 0
            if (!exists) {
                Failable.Failure(
                    DemoError(
                        DemoErrorType.DEMO_DOES_NOT_EXIST,
                        "No demo exists with id ${demo.id}"
                    )
                )
            } else {
                it.preparedQueryAwait(
                    "update demo.demos set name = $2 where id = $1",
                    Tuple.of(demo.id, demo.name)
                )
                Failable.Success(demo)
            }
        }
    }
}

suspend fun <T> PgPool.transactionallyAwait(applier: suspend (Transaction) -> T): T {
    var transaction: Transaction? = null
    try {
        transaction = beginAwait()
        val result = applier(transaction)
        transaction.commit()
        return result
    }
    finally {
        transaction?.close()
    }
}




