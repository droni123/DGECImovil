package mx.unam.unaminternacional.dgecimovil.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiLogin(
    @SerializedName("error")
    var error:ArrayList<String> ? = ArrayList<String>(),
    @SerializedName("tipo_user")
    var tipo_user:Int ? = 0,
    @SerializedName("token_app")
    var token_app:String ? = "",
): Serializable {
}