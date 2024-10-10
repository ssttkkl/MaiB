package io.ssttkkl.maib.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaiRecord(
    @SerialName("achievements")
    val achievements: Double = 0.0,
    @SerialName("ds")
    val ds: Double = 0.0,
    @SerialName("dxScore")
    val dxScore: Int = 0,
    @SerialName("fc")
    val fc: String = "",
    @SerialName("fs")
    val fs: String = "",
    @SerialName("level")
    val level: String = "",
    @SerialName("level_index")
    val levelIndex: Int = 0,
    @SerialName("level_label")
    val levelLabel: String = "",
    @SerialName("ra")
    val ra: Int = 0,
    @SerialName("rate")
    val rate: String = "",
    @SerialName("song_id")
    val songId: Int = 0,
    @SerialName("title")
    val title: String = "",
    @SerialName("type")
    val type: String = ""
)