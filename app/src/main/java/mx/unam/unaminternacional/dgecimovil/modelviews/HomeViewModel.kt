package mx.unam.unaminternacional.dgecimovil.modelviews

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.trails.AppDgeciMovil
import mx.unam.unaminternacional.dgecimovil.trails.BdSqlHelper
import mx.unam.unaminternacional.dgecimovil.trails.Constantes

/**
 * Creado por De la Cruz Hernández Idelfonso el 12/03/23
 */
class HomeViewModel : ViewModel() {
    private lateinit var bdHelper: BdSqlHelper
    val loader = MutableLiveData<Boolean>()
    //val bandera
    private var con_internet = true
    init {
        val connectivityManager = AppDgeciMovil.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager?.let {
            it.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    if(!con_internet){
                        Toast.makeText(
                            AppDgeciMovil.instance,
                            AppDgeciMovil.instance.getString(R.string.REGRESO_INTERNET), Toast.LENGTH_SHORT).show()
                        con_internet = true
                    }
                }
                override fun onLost(network: Network) {
                    con_internet = false
                    Toast.makeText(
                        AppDgeciMovil.instance,
                        AppDgeciMovil.instance.getString(R.string.SIN_INTERNET), Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun cerrarSession(){
        bdHelper = BdSqlHelper()
        bdHelper.delConfig(Constantes.KEY_BD_USER)
    }
}