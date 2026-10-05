package com.maadcoding.weatherapp

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await

class AndroidLocatable(val context: Context) : Locatable {
    @SuppressLint("MissingPermission")
    override suspend fun getLocation(): Location {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        val location = fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        )
            .await() ?: throw Exception("Unable to get current location")

        return Location(location.latitude, location.longitude)
    }
}

@Composable
actual fun rememberLocatable(): Locatable {
    val context = LocalContext.current
    return remember(context) { AndroidLocatable(context) }
}