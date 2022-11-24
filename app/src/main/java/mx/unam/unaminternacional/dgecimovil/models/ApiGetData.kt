package mx.unam.unaminternacional.dgecimovil.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiGetData(
    @SerializedName("status")
    var status: Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var data : ApiTest?= ApiTest("")
) : Serializable {}

