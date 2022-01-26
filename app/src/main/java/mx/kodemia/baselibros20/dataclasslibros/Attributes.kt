package mx.kodemia.baselibros20.dataclasslibros

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attributes(
    val title: String,
    val slug: String,
    val content: String,
    @SerialName("created-at")
    val createdat: String,
    @SerialName("updated-at")
    val updatedat: String
)
