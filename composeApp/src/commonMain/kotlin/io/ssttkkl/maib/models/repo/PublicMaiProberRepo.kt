package io.ssttkkl.maib.models.repo

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ssttkkl.maib.models.MaiMusic
import io.ssttkkl.maib.models.MaiRecordsResp
import io.ssttkkl.maib.models.QueryPlayerResp
import kotlinx.io.Buffer
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

object PublicMaiProberRepo {
    private const val baseUrl = "https://www.diving-fish.com/api/maimaidxprober"

    suspend fun getMusicData(): List<MaiMusic> {
        return HttpClientProvider.client.get(
            "$baseUrl/music_data"
        ).body()
    }

    suspend fun queryPlayerRecords(username: String): QueryPlayerResp {
        return HttpClientProvider.client.post(
            "$baseUrl/query/player"
        ) {
            contentType(ContentType.Application.Json)
            setBody(buildJsonObject {
                put("username", username)
            })
        }.body()
    }
}