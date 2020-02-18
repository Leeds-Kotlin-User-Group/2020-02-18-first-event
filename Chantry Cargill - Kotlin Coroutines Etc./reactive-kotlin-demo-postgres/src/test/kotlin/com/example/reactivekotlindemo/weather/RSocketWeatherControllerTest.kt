package com.example.reactivekotlindemo.weather

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.test.StepVerifier
import java.net.URI

@SpringBootTest
@ExtendWith(SpringExtension::class)
internal class RSocketWeatherControllerTest(@Autowired private val rSocketRequesterBuilder: RSocketRequester.Builder) {
    private val rSocketRequester = rSocketRequesterBuilder.connectWebSocket(URI.create("ws://localhost:8082/rsocket"))
    @Test
    fun testApi() {
        val request = rSocketRequester.map { it.route(LEEDS_ROUTE) }
            .flatMapMany { it.retrieveFlux(WeatherReading::class.java) }
        StepVerifier.create(request)
            .consumeNextWith { reading -> println(reading) }
            .thenCancel()
            .verify()
    }
}