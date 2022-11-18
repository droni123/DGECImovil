package mx.unam.unaminternacional.dgecimovil.activitys.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import mx.unam.unaminternacional.dgecimovil.R

class AuthMensaje : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_mensaje)

        val fragmentList = arrayListOf(
            AuthBienvenidaFragment(),
            AuthInstruccionesFragment(),
            AuthLoginFragment()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            supportFragmentManager,
            lifecycle
        )
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = adapter

    }
}