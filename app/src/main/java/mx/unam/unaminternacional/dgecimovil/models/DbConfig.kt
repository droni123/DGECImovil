package mx.unam.unaminternacional.dgecimovil.models

import java.io.Serializable

data class DbConfig(
    val id_config : Int ?= 0,
    val key : String,
    val value : String ? = null,
    val status : Int ?= 0
): Serializable {
}