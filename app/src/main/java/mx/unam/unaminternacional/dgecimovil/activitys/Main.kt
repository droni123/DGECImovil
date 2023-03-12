package mx.unam.unaminternacional.dgecimovil.activitys

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.lifecycleScope
import me.ibrahimsn.particle.ParticleView
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.activitys.auth.AuthMensaje
import mx.unam.unaminternacional.dgecimovil.databinding.ActivityMainBinding
import mx.unam.unaminternacional.dgecimovil.interfaces.ApiInterface
import mx.unam.unaminternacional.dgecimovil.modelviews.MainViewModel
import mx.unam.unaminternacional.dgecimovil.trails.ApiClient
import mx.unam.unaminternacional.dgecimovil.trails.AppDgeciMovil
import mx.unam.unaminternacional.dgecimovil.trails.BdSqlHelper
import mx.unam.unaminternacional.dgecimovil.trails.Constantes

class Main : AppCompatActivity() {
    private lateinit var vi: ActivityMainBinding
    private lateinit var particleView: ParticleView
    private lateinit var anima: AnimatedVectorDrawable
    private val MainViewModel : MainViewModel by viewModels()

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
        MainViewModel.getConfiguracionInicial()
        MainViewModel.datos.observe(this@Main){
            if(it.value.isNullOrEmpty()){
                //INICIAMOS AUTH
                Handler(Looper.getMainLooper()).postDelayed({
                    val vistaAuth = Intent(context, AuthMensaje::class.java)
                    startActivity(vistaAuth)
                    finish()
                    overridePendingTransition( 0, R.drawable.fade_screen );
                }, Constantes.KEY_TIEMPO_DE_ESPERA_STAR)
            }else{
                MainViewModel.delete()
                val vistaWelcome = Intent(AppDgeciMovil.instance, Home::class.java)
                startActivity(vistaWelcome)
                finish()
                overridePendingTransition( 0, R.drawable.fade_screen );
            }
        }
    }
    fun showMensaje(mensaje:String,context: Context){
        Toast.makeText( context, mensaje, Toast.LENGTH_LONG ).show()
    }
}