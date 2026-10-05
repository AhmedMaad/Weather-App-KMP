package com.maadcoding.weatherapp.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

suspend fun getWeather(lat: Double, lon: Double): Double {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }
    val response: Weather =
        client
            .get("http://api.weatherapi.com/v1/current.json?key=${API_KEY}&q=$lat,$lon")
            .body()
    client.close()
    return response.current.temperature
}