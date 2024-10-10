package io.ssttkkl.maib.models.repo

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.setCookie
import io.ssttkkl.maib.models.MaiProberLoginResp
import io.ssttkkl.maib.models.MaiRecordsResp
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.concurrent.Volatile

object MaiProberRepo {
    private const val baseUrl = "https://www.diving-fish.com/api/maimaidxprober"

    @Volatile
    private var jwtToken: String? = null

    suspend fun login(username: String, password: String): MaiProberLoginResp {
        val response = HttpClientProvider.client.post(
            "$baseUrl/login"
        ) {
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("username", username)
                put("password", password)
            })
        }

        jwtToken = response.setCookie().firstOrNull { it.name == "jwt_token" }?.value
            .let {
                checkNotNull(it) { "jwt_token not found in response set-cookie" }
            }

        return response.body()
    }

    suspend fun getRecords(): MaiRecordsResp {
        return HttpClientProvider.client.get(
            "$baseUrl/player/records"
        ) {
            jwtToken?.let {
                header("cookie", "jwt_token=${it}")
            }
        }.body()
    }
}