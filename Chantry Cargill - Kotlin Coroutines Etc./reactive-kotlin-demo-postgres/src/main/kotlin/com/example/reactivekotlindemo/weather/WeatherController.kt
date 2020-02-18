package com.example.reactivekotlindemo.weather

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import reactor.core.publisher.Mono

const val LEEDS_URI = "http://api.openweathermap.org/data/2.5/weather?q=Leeds,uk&APPID=323e004e6feca7d8bba19cee16c1ca57"
const val LONDON_URI = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=323e004e6feca7d8bba19cee16c1ca57"

@RestController
class WeatherController(private val webClient: WebClient) {
    @GetMapping("/weather")
    suspend fun getWeather() = coroutineScope {
        val leeds = async {
            webClient.get().uri(LEEDS_URI)
                .accept(APPLICATION_JSON)
                .awaitExchange()
                .awaitBody<WeatherReading>()
        }

        val london = async {
            webClient.get().uri(LONDON_URI)
                .accept(APPLICATION_JSON)
                .awaitExchange()
                .awaitBody<WeatherReading>()
        }

        listOf(leeds.await(), london.await())
    }

    @GetMapping("/leeds")
    suspend fun getLeedsWeather() = webClient.get().uri(LEEDS_URI)
        .accept(APPLICATION_JSON)
        .awaitExchange()
        .awaitBody<WeatherReading>()

    /**
     * Demonstrates the difference when dealing with reactive wrappers
     */
    @GetMapping("/weather2")
    fun getWeather2(): Mono<List<WeatherReading>> {
        val london = webClient.get().uri(LONDON_URI)
            .accept(APPLICATION_JSON)
            .exchange()
            .flatMap { r -> r.bodyToMono(WeatherReading::class.java) }

        return webClient.get().uri(LEEDS_URI)
            .accept(APPLICATION_JSON)
            .exchange()
            .flatMap { r -> r.bodyToMono(WeatherReading::class.java) }
            .zipWith(london) { left, right -> listOf(left, right) }
    }
}