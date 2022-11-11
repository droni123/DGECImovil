package mx.unam.unaminternacional.dgecimovil

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ANIMACION
        var animacion = findViewById<ImageView>(R.id.loadIng)
        animacion.setImageResource(R.drawable.loadb)
        val anima = animacion.drawable as AnimatedVectorDrawable
        anima.start()

    }
}