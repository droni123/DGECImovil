package mx.unam.unaminternacional.dgecimovil.activitys

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.activitys.configuracion.ConfiguracionFragment
import mx.unam.unaminternacional.dgecimovil.activitys.creditos.CreditosFragment
import mx.unam.unaminternacional.dgecimovil.activitys.mensajes.MensajesFragment
import mx.unam.unaminternacional.dgecimovil.activitys.perfil.PerfilFragment
import mx.unam.unaminternacional.dgecimovil.databinding.ActivityHomeBinding
import mx.unam.unaminternacional.dgecimovil.trails.BdSqlHelper
import mx.unam.unaminternacional.dgecimovil.trails.Constantes
import mx.unam.unaminternacional.dgecimovil.ui.DGECITheme


class Home : AppCompatActivity() {
    private lateinit var vi: ActivityHomeBinding
    private val mutable = MutableLiveData<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vi = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(vi.root)
        mostrarFragmento(MensajesFragment())
        vi.homeTop.setContent {
            MenuTopHome()
        }
        vi.homeMenu.setContent {
            MenuHome()
        }
    }
    private fun mostrarFragmento(fragment: Fragment){
        val iniciaFragment = supportFragmentManager.beginTransaction()
        iniciaFragment.replace(R.id.HomeRoot, fragment)
        iniciaFragment.commit()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview
    private fun MenuTopHome() {
        DGECITheme() {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Bienvenido: Idelfonso",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light
                    )
                },
                navigationIcon = {
                    var expanded by remember { mutableStateOf(false) }
                    IconButton(onClick = {
                        expanded = true
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ){
                        DropdownMenuItem(
                            text = { Text("Creditos") },
                            onClick = {
                                expanded = false
                                mostrarFragmento( CreditosFragment() )
                                mutable.postValue(false)
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Filled.Star,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        mostrarFragmento( PerfilFragment() )
                        mutable.postValue(false)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Photo,
                            contentDescription = "Perfil"
                        )
                    }
                },
            )
        }
    }
    @Composable
    @Preview
    private fun MenuHome() {
        DGECITheme() {
            var selectedItem by remember { mutableStateOf(0) }
            val items = listOf("Mensajes", "Configuración")
            val itemsIco = listOf(
                Icons.Filled.Mail,
                Icons.Filled.Token
            )
            NavigationBar() {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(itemsIco[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            when(items[index]){
                                items[0] -> {
                                    mostrarFragmento( MensajesFragment() )
                                    mutable.postValue(true)
                                }
                                items[1] -> {
                                    mostrarFragmento( ConfiguracionFragment() )
                                    mutable.postValue(true)
                                }
                            }
                        }
                    )
                    this@Home.mutable.observe(this@Home) {  if (!it) { selectedItem = -1  }  }
                }
            }
        }
    }
}