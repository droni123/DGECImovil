package mx.unam.unaminternacional.dgecimovil.activitys

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.lifecycleScope
import me.ibrahimsn.particle.ParticleView
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.activitys.auth.AuthMensaje
import mx.unam.unaminternacional.dgecimovil.databinding.ActivityMainBinding
import mx.unam.unaminternacional.dgecimovil.interfaces.ApiInterface
import mx.unam.unaminternacional.dgecimovil.trails.ApiClient
import mx.unam.unaminternacional.dgecimovil.trails.BdSqlHelper

class Main : AppCompatActivity() {
    private lateinit var vi: ActivityMainBinding
    private lateinit var particleView: ParticleView
    private lateinit var anima: AnimatedVectorDrawable
    private lateinit var bdHelper: BdSqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vi = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vi.root)
        vi.loadTxt.text = HtmlCompat.fromHtml(getString(R.string.app_load),HtmlCompat.FROM_HTML_MODE_LEGACY)
        //ANIMACION
        val animacion = vi.loadImg
        animacion.setImageResource(R.drawable.loadb)
        anima = animacion.drawable as AnimatedVectorDrawable
        anima.start()

        particleView = vi.particleView

        inicializarAuth(this@Main)
    }
    override fun onResume() {
        super.onResume()
        anima.start()
        particleView.resume()
    }

    override fun onPause() {
        super.onPause()
        anima.stop()
        particleView.pause()
    }

    fun inicializarAuth(context: Context) {
        bdHelper = BdSqlHelper(context)
        val datos = bdHelper.getConfig("tokenA")
        if(datos.value.isNullOrEmpty()){
            //INICIAMOS AUTH
            Handler(Looper.getMainLooper()).postDelayed({
                val vistaAuth = Intent(context, AuthMensaje::class.java)
                startActivity(vistaAuth)
                finish()
                overridePendingTransition( 0, R.drawable.fade_screen );
            }, 500)
        }else{
            val KEY_API = "oY4fG0WPTzvNzdg2BLTUpqbZrTXZbrJ89su6r8YzJjZy9IWrxTYRZ9Af7QG9"
            val TAG = getString(R.string.dgeci)
            var apiCliente = ApiClient.getInstancia()
            var apiInstancia = apiCliente.create(ApiInterface::class.java)
            lifecycleScope.launchWhenCreated {
                try {
                    //val data = DbConfig(key = "Nombre", value = "Idelfonso")
                    //bdHelper.savConfig(data)
                    //val datos = bdHelper.getConfig("Nombre")
                    //bdHelper.delConfig("Nombre")
                    val response = apiInstancia.getTest(KEY_API)
                    if (response.isSuccessful) {
                        showMensaje("${response.body().toString()}",context)
                    }else{
                        Log.e(TAG,response.toString())
                    }
                }catch (ex:Exception){
                    Log.e(TAG,ex.toString())
                }
            }
        }
    }
    fun showMensaje(mensaje:String,context: Context){
        Toast.makeText( context, mensaje, Toast.LENGTH_LONG ).show()
    }
}