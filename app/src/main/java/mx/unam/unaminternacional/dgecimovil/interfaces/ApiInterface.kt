package mx.unam.unaminternacional.dgecimovil.interfaces

import mx.unam.unaminternacional.dgecimovil.models.ApiGetData
import mx.unam.unaminternacional.dgecimovil.trails.Constantes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    @POST("/api")
    @FormUrlEncoded
    suspend fun login(
        @Field("email")
        usuario:String,
        @Field("password")
        password:String,
        @Field("token")
        token:String,
        @Field("key")
        key:String = Constantes.KEY_API,
        @Field("consulta")
        consulta:String = "login"
    ): Response<ApiGetData>
}