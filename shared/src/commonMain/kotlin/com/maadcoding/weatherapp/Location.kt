package com.maadcoding.weatherapp

import androidx.compose.runtime.Composable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface Locatable {
    suspend fun getLocation(): Location
}

@Serializable
data class Location(
    @SerialName("lat")
    val latitude: Double,
    @SerialName("lon")
    val longitude: Double
)

@Composable
expect fun rememberLocatable(): Locatable