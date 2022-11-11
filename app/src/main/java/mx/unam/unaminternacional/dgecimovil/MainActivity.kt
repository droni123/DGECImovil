package mx.unam.unaminternacional.dgecimovil

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import me.ibrahimsn.particle.ParticleView

class MainActivity : AppCompatActivity() {
    private lateinit var particleView: ParticleView
    private lateinit var anima: AnimatedVectorDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.loadTxt)
        textView.text = HtmlCompat.fromHtml(getString(R.string.app_load),HtmlCompat.FROM_HTML_MODE_LEGACY)

        //ANIMACION
        val animacion = findViewById<ImageView>(R.id.loadImg)
        animacion.setImageResource(R.drawable.loadb)
        anima = animacion.drawable as AnimatedVectorDrawable
        anima.start()

        particleView = findViewById<ParticleView>(R.id.particleView)
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
}