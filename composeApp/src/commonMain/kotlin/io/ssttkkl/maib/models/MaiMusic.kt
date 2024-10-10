package io.ssttkkl.maib.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaiMusic(
    @SerialName("basic_info")
    val basicInfo: BasicInfo = BasicInfo(),
    @SerialName("charts")
    val charts: List<MaiChart> = listOf(),
    @SerialName("cids")
    val cids: List<Int> = listOf(),
    @SerialName("ds")
    val ds: List<Double> = listOf(),
    @SerialName("id")
    val id: String = "",
    @SerialName("level")
    val level: List<String> = listOf(),
    @SerialName("title")
    val title: String = "",
    @SerialName("type")
    val type: String = ""
) {
    @Serializable
    data class BasicInfo(
        @SerialName("artist")
        val artist: String = "",
        @SerialName("bpm")
        val bpm: Int = 0,
        @SerialName("from")
        val from: String = "",
        @SerialName("genre")
        val genre: String = "",
        @SerialName("is_new")
        val isNew: Boolean = false,
        @SerialName("release_date")
        val releaseDate: String = "",
        @SerialName("title")
        val title: String = ""
    )
}