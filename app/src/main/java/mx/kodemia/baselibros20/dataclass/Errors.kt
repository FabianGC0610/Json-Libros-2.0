package mx.kodemia.baselibros20.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class Errors(
    val errors: List<Error>
    )
