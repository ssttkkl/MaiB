package io.ssttkkl.maib.models.repo

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientProvider {
    val client = HttpClient(HttpClientProvider.factory) {
        expectSuccess = true
        install(Logging) {
            level = LogLevel.ALL
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
            logger = object: Logger {
                override fun log(message: String) {
                    println("HTTP Client: $message")
                }
            }
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
}

expect val HttpClientProvider.factory: HttpClientEngineFactory<*>
