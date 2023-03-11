package mx.unam.unaminternacional.dgecimovil.activitys.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import me.ibrahimsn.particle.ParticleView
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.databinding.FragmentInicioSecionBinding
import mx.unam.unaminternacional.dgecimovil.ui.DGECITheme
import mx.unam.unaminternacional.dgecimovil.ui.XaC
import mx.unam.unaminternacional.dgecimovil.ui.Xbb
import mx.unam.unaminternacional.dgecimovil.ui.Xbb25
import java.util.regex.Pattern

class AuthLoginFragment : Fragment() {

    private var _binding : FragmentInicioSecionBinding?= null

    private val binding get() = _binding!!
    private lateinit var particleView: ParticleView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioSecionBinding.inflate(inflater,container,false)
        val view = binding.root
        particleView = binding.particleView
        binding.formularioIs.setContent {
            formularioInicio()
        }
        /*setContent {
            formularioInicio()
        }*/
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
    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(name = "Formulario")
    @Composable
    private fun formularioInicio(){
        DGECITheme() {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var color_input = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Xbb25,
                    focusedLabelColor = Xbb,
                    textColor = Xbb
                )
                var usuario by rememberSaveable { mutableStateOf("") }
                var pass by rememberSaveable { mutableStateOf("") }
                var passVisible by rememberSaveable { mutableStateOf(false) }
                var token by rememberSaveable { mutableStateOf("") }
                var openDialog = remember { mutableStateOf(false)  }
                var mensDialig = remember { mutableStateOf("") }
                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    singleLine = true,
                    label = { Text("${getString(R.string.authCorreo)} / ${getString(R.string.authNcuenta)}") },
                    colors = color_input,
                    leadingIcon = {
                        Icon(imageVector  = Icons.Filled.VerifiedUser, "${getString(R.string.authCorreo)} / ${getString(R.string.authNcuenta)}")
                    },
                    modifier = Modifier.padding(15.dp)
                )
                OutlinedTextField(
                    value = pass,
                    onValueChange = { pass = it },
                    label = { Text(getString(R.string.authPass)) },
                    singleLine = true,
                    //placeholder = { Text("Password") },
                    visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (passVisible)  Icons.Filled.Visibility  else Icons.Filled.VisibilityOff
                        val description = if (passVisible) "${getString(R.string.esconder)} ${getString(R.string.authPass)}" else "${getString(R.string.mostrar)} ${getString(R.string.authPass)}"
                        IconButton(onClick = {passVisible = !passVisible}){
                            Icon(imageVector  = image, description)
                        }
                    },
                    colors = color_input,
                    leadingIcon = {
                        Icon(imageVector  = Icons.Filled.Password, getString(R.string.authPass))
                    },
                    modifier = Modifier.padding(15.dp)
                )
                OutlinedTextField(
                    value = token,
                    onValueChange = { token = it },
                    singleLine = true,
                    label = { Text(getString(R.string.authToken)) },
                    colors = color_input,
                    leadingIcon = {
                        Icon(imageVector  = Icons.Filled.Token, getString(R.string.authToken))
                    },
                    modifier = Modifier.padding(15.dp)
                )
                ElevatedButton(
                    onClick = {
                        var iniciar_login = true
                        mensDialig.value = ""
                        if( usuario.isEmpty() ){
                            iniciar_login = false
                            mensDialig.value += "${getString(R.string.el_campo)} ${getString(R.string.authCorreo)} / ${getString(R.string.authNcuenta)} ${getString(R.string.obligatorio)}"
                        }
                        if( pass.isEmpty() ){
                            iniciar_login = false
                            mensDialig.value += (if (mensDialig.value.isEmpty()) "" else "\n")+"${getString(R.string.el_campo)} ${getString(R.string.authPass)} ${getString(R.string.obligatorio)}"
                        }
                        if( token.isEmpty() ){
                            iniciar_login = false
                            mensDialig.value += (if (mensDialig.value.isEmpty()) "" else "\n")+"${getString(R.string.el_campo)} ${getString(R.string.authToken)} ${getString(R.string.obligatorio)}"
                        }
                        if(iniciar_login){
                            val pattern_ncta = Pattern.compile( "^[0-9]{9}$", Pattern.UNICODE_CASE)
                            val is_emal = Patterns.EMAIL_ADDRESS.matcher(usuario).matches()
                            val is_n_cuenta = pattern_ncta.matcher(usuario).matches() && usuario.length == 9
                            if( !is_emal && !is_n_cuenta ){
                                iniciar_login = false
                                mensDialig.value += "${getString(R.string.el_campo)} ${getString(R.string.authCorreo)} / ${getString(R.string.authNcuenta)} ${getString(R.string.debe_ser_valido)}"
                            }
                        }
                        if(!iniciar_login){
                            openDialog.value = true
                        }else{
                            Log.e("DRONI",usuario)
                            Log.e("DRONI",pass)
                            Log.e("DRONI",token)
                            /*
                        //Handler(Looper.getMainLooper()).postDelayed({
                            val vistaWelcome = Intent(context, Home::class.java)
                            startActivity(vistaWelcome)
                            activity?.finish()
                            activity?.overridePendingTransition( 0, R.drawable.fade_screen );
                        //}, 500)
                        */
                        }
                    },
                    modifier = Modifier.padding(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = XaC,
                        contentColor = Xbb
                    )
                ) {
                    Text(
                        text = getString(R.string.iniciar_sesion),
                        fontSize = 20.sp
                    )
                    Icon(imageVector = Icons.Filled.Login,getString(R.string.iniciar_sesion))
                }
                ////////////////ALERT
                if (openDialog.value) {
                    Log.e("DRONI",mensDialig.value)
                    openDialog.value = false
                    /*
                    AlertDialog(
                        onDismissRequest = { openDialog.value = false },
                        title = {
                            Text(text = getString(R.string.advertencia))
                        },
                        text = {
                            Text( mensDialig.value )
                        },
                        dismissButton = {
                            Button(
                                onClick = {
                                    openDialog.value = false
                                }) {
                                Text(getString(R.string.aceptar))
                            }
                        }
                    )
                    */
                }


            }
        }
    }
}