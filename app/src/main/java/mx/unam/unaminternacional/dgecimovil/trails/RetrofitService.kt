package mx.unam.unaminternacional.dgecimovil.trails

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Creado por De la Cruz Hernández Idelfonso el 11/03/23
 */
object RetrofitService {
    private var INSTANCE: Retrofit? = null
    fun getRetrofit(): Retrofit {
        /*
        val apiInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient
            .Builder()
            .addInterceptor(apiInterceptor)
            .build()*/

        return INSTANCE ?: synchronized(this){
            val instance = Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //.client(httpClient)
                .build()
            INSTANCE = instance
            instance
        }
    }
}