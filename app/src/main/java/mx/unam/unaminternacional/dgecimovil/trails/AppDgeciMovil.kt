package mx.unam.unaminternacional.dgecimovil.trails

import android.app.Application

class AppDgeciMovil : Application() {
    override fun onCreate(){
        super.onCreate()
        instance = this
    }
    companion object{
        lateinit var instance: AppDgeciMovil
            private set
    }
}