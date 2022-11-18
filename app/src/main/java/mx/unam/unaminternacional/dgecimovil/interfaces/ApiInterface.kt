package mx.unam.unaminternacional.dgecimovil.interfaces

import mx.unam.unaminternacional.dgecimovil.models.ApiGetData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    @POST("/api")
    @FormUrlEncoded
    suspend fun getTest(
        @Field("key")
        key : String,
        @Field("consulta")
        consulta : String ?= "test"
    ): Response<ApiGetData>
}