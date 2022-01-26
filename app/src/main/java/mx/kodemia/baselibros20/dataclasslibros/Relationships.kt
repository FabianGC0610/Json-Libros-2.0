package mx.kodemia.baselibros20.dataclasslibros
import kotlinx.serialization.Serializable

@Serializable
data class Relationships(
    val authors: Authors,
    val categories: Categories
)
