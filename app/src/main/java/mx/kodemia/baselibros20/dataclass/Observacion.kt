package mx.kodemia.baselibros20.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class Observacion(
    val fecha: String,
    val comentario: String
)
