package mx.kodemia.baselibros20.dataclasslibros

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val self: String,
    val related: String = ""
): java.io.Serializable // Para pasar datos entre Activities o Fragments
