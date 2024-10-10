package io.ssttkkl.maib.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaiRecordsResp(
    @SerialName("additional_rating")
    val additionalRating: Int = 0,
    @SerialName("nickname")
    val nickname: String = "",
    @SerialName("plate")
    val plate: String = "",
    @SerialName("rating")
    val rating: Int = 0,
    @SerialName("records")
    val records: List<MaiRecord> = listOf(),
    @SerialName("username")
    val username: String = ""
)