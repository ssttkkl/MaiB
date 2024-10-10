package io.ssttkkl.maib.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryPlayerResp(
    @SerialName("additional_rating")
    val additionalRating: Int = 0,
    @SerialName("nickname")
    val nickname: String = "",
    @SerialName("plate")
    val plate: String = "",
    @SerialName("rating")
    val rating: Int = 0,
    @SerialName("charts")
    val charts: Charts = Charts(),
    @SerialName("username")
    val username: String = ""
) {
    @Serializable
    data class Charts(
        val dx: List<MaiRecord> = listOf(),
        val sd: List<MaiRecord> = listOf()
    )
}