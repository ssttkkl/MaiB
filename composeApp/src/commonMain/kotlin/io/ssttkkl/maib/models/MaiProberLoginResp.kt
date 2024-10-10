package io.ssttkkl.maib.models

import kotlinx.serialization.Serializable

@Serializable
data class MaiProberLoginResp(
    val errcode: Int = 0,
    val message: String = ""
)