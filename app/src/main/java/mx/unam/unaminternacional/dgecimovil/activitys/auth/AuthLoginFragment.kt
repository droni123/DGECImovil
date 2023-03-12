package mx.unam.unaminternacional.dgecimovil.activitys.auth

import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import me.ibrahimsn.particle.ParticleView
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.activitys.Home
import mx.unam.unaminternacional.dgecimovil.databinding.FragmentInicioSecionBinding
import mx.unam.unaminternacional.dgecimovil.modelviews.MainViewModel
import mx.unam.unaminternacional.dgecimovil.trails.AppDgeciMovil
import mx.unam.unaminternacional.dgecimovil.trails.Constantes
import mx.unam.unaminternacional.dgecimovil.ui.DGECITheme
import mx.unam.unaminternacional.dgecimovil.ui.XaC
import mx.unam.unaminternacional.dgecimovil.ui.Xbb
import mx.unam.unaminternacional.dgecimovil.ui.Xbb25
import java.util.regex.Pattern

class AuthLoginFragment : Fragment() {

    private var _binding : FragmentInicioSecionBinding?= null

    private val binding get() = _binding!!
    private lateinit var particleView: ParticleView
    private val MainViewModel : MainViewModel by viewModels()
    private lateinit var anima: AnimatedVectorDrawable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioSecionBinding.inflate(inflater,container,false)
        val view = binding.root
        particleView = binding.particleView
        val animacion = binding.loadImg
        animacion.setImageResource(R.drawable.loadb)
        anima = animacion.drawable as AnimatedVectorDrawable

        binding.formularioIs.setContent {
            formularioInicio()
        }
        return view
    }

    override fun onPause() {
        super.onPause()
        particleView.pause()
    }

    override fun onResume() {
        super.onResume()
        particleView.resume()
    }
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationGraphicsApi::class)
    @Preview(name = "Formulario")
    @Composable
    private fun formularioInicio(){
        var visible by remember { mutableStateOf(true) }
        val openDialog = remember { mutableStateOf(false)  }
        val mensDialig = remember { mutableStateOf("") }

        DGECITheme() {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val color_input = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Xbb25,
                    focusedLabelColor = Xbb,
                    textColor = Xbb
                )
                var usuario by rememberSaveable { mutableStateOf("") }
                var pass by rememberSaveable { mutableStateOf("") }
                var passVisible by rememberSaveable { mutableStateOf(false) }
                var token by rememberSaveable { mutableStateOf("") }
                val stringUsuario =  "${stringResource(R.string.authCorreo)} / ${stringResource(R.string.authNcuenta)}"
                val stringContrasena = stringResource(R.string.authPass)
                val stringEsconder = stringResource(R.string.esconder)
                val stringMostrar = stringResource(R.string.mostrar)
                val stringToken = stringResource(R.string.authToken)
                val stringElcampo = stringResource(R.string.el_campo)
                val stringObligatorio = stringResource(R.string.obligatorio)
                val stringValido = stringResource(R.string.debe_ser_valido)
                val stringIniciarSeccion = stringResource(R.string.iniciar_sesion)
                if(visible){
                    OutlinedTextField(
                        value = usuario,
                        onValueChange = { usuario = it },
                        singleLine = true,
                        label = { Text(stringUsuario) },
                        colors = color_input,
                        leadingIcon = {
                            Icon(imageVector  = Icons.Filled.VerifiedUser, stringUsuario)
                        },
                        modifier = Modifier.padding(15.dp)
                    )
                    OutlinedTextField(
                        value = pass,
                        onValueChange = { pass = it },
                        label = { Text(stringContrasena) },
                        singleLine = true,
                        visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image = if (passVisible)  Icons.Filled.Visibility  else Icons.Filled.VisibilityOff
                            val description = if (passVisible) "${stringEsconder} ${stringContrasena}" else "${stringMostrar} ${stringContrasena}"
                            IconButton(onClick = {passVisible = !passVisible}){
                                Icon(imageVector  = image, description)
                            }
                        },
                        colors = color_input,
                        leadingIcon = {
                            Icon(imageVector  = Icons.Filled.Password, stringContrasena)
                        },
                        modifier = Modifier.padding(15.dp)
                    )
                    OutlinedTextField(
                        value = token,
                        onValueChange = { token = it },
                        singleLine = true,
                        label = { Text(stringToken) },
                        colors = color_input,
                        leadingIcon = {
                            Icon(imageVector  = Icons.Filled.Token, stringToken)
                        },
                        modifier = Modifier.padding(15.dp)
                    )
                    ElevatedButton(
                        onClick = {
                            var iniciar_login = true
                            mensDialig.value = ""
                            if( usuario.isEmpty() ){
                                iniciar_login = false
                                mensDialig.value += "${stringElcampo} ${stringUsuario} ${stringObligatorio}"
                            }
                            if( pass.isEmpty() ){
                                iniciar_login = false
                                mensDialig.value += (if (mensDialig.value.isEmpty()) "" else "\n")+"${stringElcampo} ${stringContrasena} ${stringObligatorio}"
                            }
                            if( token.isEmpty() ){
                                iniciar_login = false
                                mensDialig.value += (if (mensDialig.value.isEmpty()) "" else "\n")+"${stringElcampo} ${stringToken} ${stringObligatorio}"
                            }
                            if(iniciar_login){
                                val pattern_ncta = Pattern.compile( "^[0-9]{9}$", Pattern.UNICODE_CASE)
                                val is_emal = Patterns.EMAIL_ADDRESS.matcher(usuario).matches()
                                val is_n_cuenta = pattern_ncta.matcher(usuario).matches() && usuario.length == 9
                                if( !is_emal && !is_n_cuenta ){
                                    iniciar_login = false
                                    mensDialig.value += "${stringElcampo} ${stringUsuario} ${stringValido}"
                                }
                            }
                            if(!iniciar_login){
                                openDialog.value = true
                            }else{
                                MainViewModel.initLogin(usuario,pass,token)

                            }
                        },
                        modifier = Modifier.padding(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = XaC,
                            contentColor = Xbb
                        )
                    ) {
                        Text(
                            text = stringIniciarSeccion,
                            fontSize = 20.sp
                        )
                        Icon(imageVector = Icons.Filled.Login,stringIniciarSeccion)
                    }
                }
                ////////////////ALERT
                if (openDialog.value) {
                    AlertDialog(
                        onDismissRequest = { openDialog.value = false },
                        title = {
                            Text(
                                text = stringResource(id = R.string.advertencia),
                                textAlign = TextAlign.Center
                            )
                        },
                        text = { Text(mensDialig.value) },
                        confirmButton = {
                            Button(
                                onClick = { openDialog.value = false }
                            ) {
                                Text( text = stringResource(id = R.string.aceptar) )
                            }
                        }
                    )
                }

            }
        }

        /////////////////
        MainViewModel.loader.observe(viewLifecycleOwner) {
            visible = !it
            if (it) {
                binding.loadImgCon.visibility = View.VISIBLE
                anima.start()
            }else {
                binding.loadImgCon.visibility = View.INVISIBLE
                anima.stop()
            }
        }
        MainViewModel.datosResponceAuth.observe(viewLifecycleOwner) { ok ->
            if ((ok.error?.size ?: 0) > 0){
                ok.error!!.forEach {
                    mensDialig.value += (if (mensDialig.value.isEmpty()) "" else "\n")+"${it}"
                }
                openDialog.value = true
            } else {
                visible = false
                startActivity()
            }
        }
    }
    fun startActivity(){
        Log.e(Constantes.LOGTAG,"DEMOS")
        activity?.let {
            val vistaWelcome = Intent(AppDgeciMovil.instance, Home::class.java)
            startActivity(vistaWelcome)
            it.finish()
            it.overridePendingTransition( 0, R.drawable.fade_screen )
        }
    }
}