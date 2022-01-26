package mx.kodemia.baselibros20.dataclasslibros

import kotlinx.serialization.Serializable

@Serializable
data class datosLibro(
    val type: String,
    val id: String,
    val attributes: Attributes,
    val relationships: Relationships,
    val links: Links
)
