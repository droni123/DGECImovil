package mx.unam.unaminternacional.dgecimovil.modelviews

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.unam.unaminternacional.dgecimovil.trails.BdSqlHelper
import mx.unam.unaminternacional.dgecimovil.models.DbConfig

class MainViewModel : ViewModel()  {

    private lateinit var bdHelper: BdSqlHelper

    val datos = MutableLiveData<DbConfig>()

    fun getConfiguracionInicial(){
        bdHelper = BdSqlHelper()
        Handler(Looper.getMainLooper()).postDelayed({
            val data = bdHelper.getConfig("tokenA")
            datos.postValue(data)
        }, 0)
    }
}