package mx.kodemia.baselibros20.dataclasslibros

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val data: MutableList<datosLibro>
): java.io.Serializable
