package mx.kodemia.baselibros20.dataclass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Empleado(
    val nombre: String,
    val telefono: String,
    val casado: Boolean,
    @SerialName("cuota_Hora")
    val cuotaHora: Double,
    @SerialName("dias-De-Semana-Trabajo")
    val diasDeSemanaTrabajo: List<String>,
    val observaciones: List<Observacion>
)
