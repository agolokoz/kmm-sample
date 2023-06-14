package dev.glk.multiplatformsample

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class SpaceXApi {

    private companion object {

        private const val Endpoint = "https://api.spacexdata.com/v5/launches"
    }

    private val json = Json {
        ignoreUnknownKeys = true
        useAlternativeNames = false
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(json)
        }
    }

    suspend fun getAllLaunches(): List<RocketLaunch> {
        return httpClient.get(Endpoint).body()
    }

    suspend fun getAllLaunchesString(): String {
        return httpClient.get(Endpoint).bodyAsText()
    }
}