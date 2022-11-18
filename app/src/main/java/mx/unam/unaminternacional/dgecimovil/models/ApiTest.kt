package mx.unam.unaminternacional.dgecimovil.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiTest(
    @SerializedName("OK")
    var OK: String,
): Serializable {
}