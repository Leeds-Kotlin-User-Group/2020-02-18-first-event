package com.example.reactivekotlindemo.weather

import org.springframework.http.MediaType
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import java.time.Duration

const val LEEDS_ROUTE = "leeds.weather";

@Controller
class RSocketWeatherController(webClient: WebClient) {
    val leeds = webClient
        .get().uri(LEEDS_URI)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .flatMap { it.bodyToMono(WeatherReading::class.java) }
        .let { leeds -> Flux.interval(Duration.ofSeconds(5))
            .flatMap { leeds }
        }
        .distinctUntilChanged { it.dt }
        .share()

    @MessageMapping(LEEDS_ROUTE)
    fun weather(): Flux<WeatherReading> = leeds
}