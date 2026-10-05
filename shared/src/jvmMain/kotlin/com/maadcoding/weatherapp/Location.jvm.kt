package com.maadcoding.weatherapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class DesktopLocatable : Locatable {
    override suspend fun getLocation(): Location {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
        val response: Location = client.get("http://ip-api.com/json").body()
        client.close()
        return Location(response.latitude, response.longitude)
    }
}

@Composable
actual fun rememberLocatable(): Locatable {
    return remember { DesktopLocatable() }
}