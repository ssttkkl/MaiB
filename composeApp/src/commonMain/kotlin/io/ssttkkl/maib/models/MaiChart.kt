package io.ssttkkl.maib.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MaiChart(
    @SerialName("charter")
    val charter: String = "",
    @SerialName("notes")
    val notes: List<Int> = listOf()
)