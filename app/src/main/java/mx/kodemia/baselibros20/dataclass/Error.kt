package mx.kodemia.baselibros20.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val status: String,
    val title: String,
    val detail: String
    )
