package mx.unam.unaminternacional.dgecimovil.modelviews

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.interfaces.ApiInterface
import mx.unam.unaminternacional.dgecimovil.models.ApiGetData
import mx.unam.unaminternacional.dgecimovil.models.ApiLogin
import mx.unam.unaminternacional.dgecimovil.trails.BdSqlHelper
import mx.unam.unaminternacional.dgecimovil.models.DbConfig
import mx.unam.unaminternacional.dgecimovil.trails.AppDgeciMovil
import mx.unam.unaminternacional.dgecimovil.trails.Constantes
import mx.unam.unaminternacional.dgecimovil.trails.RetrofitService
class MainViewModel : ViewModel()  {
    private lateinit var bdHelper: BdSqlHelper

    val datos = MutableLiveData<DbConfig>()
    val loader = MutableLiveData<Boolean>()
    val datosResponceAuth = MutableLiveData<ApiLogin>()

    //val bandera
    private var con_internet = true
    init {
        val connectivityManager = AppDgeciMovil.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager?.let {
            it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    if(!con_internet){
                        Toast.makeText(AppDgeciMovil.instance,AppDgeciMovil.instance.getString(R.string.REGRESO_INTERNET), Toast.LENGTH_SHORT).show()
                        con_internet = true
                    }
                }
                override fun onLost(network: Network) {
                    con_internet = false
                    Toast.makeText(AppDgeciMovil.instance,AppDgeciMovil.instance.getString(R.string.SIN_INTERNET), Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    fun getConfiguracionInicial(){
        bdHelper = BdSqlHelper()
        Handler(Looper.getMainLooper()).postDelayed({
            val data = bdHelper.getConfig(Constantes.KEY_BD_USER)
            datos.postValue(data)
        }, Constantes.KEY_TIEMPO_DE_ESPERA_STAR)
    }
    fun initLogin(usuario:String,pass:String,token:String){
        loader.postValue(true)
        val datosBlancos = ApiLogin()
        if(con_internet) {
            Handler(Looper.getMainLooper()).postDelayed({
                viewModelScope.launch {
                    val response = RetrofitService
                        .getRetrofit()
                        .create(ApiInterface::class.java)
                        .login(usuario = usuario, password = pass, token = token)
                    if (response.isSuccessful) {
                        if (response.body() is ApiGetData) {
                            val dataAuth = response.body()!!.data
                            if (dataAuth.error!!.size == 0) {
                                //AUTH OK
                                //SAVE TOKEN
                                val dataToSave = DbConfig(key = Constantes.KEY_BD_USER,value = dataAuth.token_app)
                                //saveDataUser(dataToSave,dataAuth)
                                bdHelper = BdSqlHelper()
                                val save = bdHelper.savConfig( dataToSave ) ////SE GUARDA
                                if(save > -1){
                                    val get = bdHelper.getConfig(Constantes.KEY_BD_USER) //SE CONFIRMA ALMACENAMIENTO
                                    if(!get.value.isNullOrEmpty()){
                                        datosResponceAuth.postValue(dataAuth)
                                    } else {
                                        datosBlancos.error?.add("2: ${AppDgeciMovil.instance.getString(R.string.ERROR)}")
                                        datosResponceAuth.postValue(datosBlancos)
                                        loader.postValue(false)
                                    }
                                } else {
                                    datosBlancos.error?.add("1: ${AppDgeciMovil.instance.getString(R.string.ERROR)}")
                                    datosResponceAuth.postValue(datosBlancos)
                                    loader.postValue(false)
                                }
                            } else {
                                datosResponceAuth.postValue(dataAuth)
                                //ERROR EN CREDENCIALES
                                loader.postValue(false)
                            }
                        } else {
                            datosBlancos.error?.add(AppDgeciMovil.instance.getString(R.string.ERROR_SERVER))
                            datosResponceAuth.postValue(datosBlancos)
                            //ERROR EN ESPUESTA
                            loader.postValue(false)
                        }
                    } else {
                        datosBlancos.error?.add(AppDgeciMovil.instance.getString(R.string.ERROR_RED))
                        datosResponceAuth.postValue(datosBlancos)
                        //ERROR EN RED
                        loader.postValue(false)
                    }
                }
            }, Constantes.KEY_TIEMPO_DE_ESPERA)
        } else {
            datosBlancos.error?.add(AppDgeciMovil.instance.getString(R.string.SIN_INTERNET))
            datosResponceAuth.postValue(datosBlancos)
            //SIN RED
            loader.postValue(false)
        }
    }
    fun delete(){
        bdHelper = BdSqlHelper()
        bdHelper.delConfig(Constantes.KEY_BD_USER)
    }
}