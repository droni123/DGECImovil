package mx.unam.unaminternacional.dgecimovil.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import kotlinx.coroutines.launch
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.activitys.configuracion.ConfiguracionFragment
import mx.unam.unaminternacional.dgecimovil.activitys.creditos.CreditosFragment
import mx.unam.unaminternacional.dgecimovil.activitys.mensajes.MensajesFragment
import mx.unam.unaminternacional.dgecimovil.activitys.perfil.PerfilFragment
import mx.unam.unaminternacional.dgecimovil.databinding.ActivityHomeBinding
import mx.unam.unaminternacional.dgecimovil.ui.theme.DGECITheme


class Home : AppCompatActivity() {
    private lateinit var vi: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vi = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(vi.root)

        mostrarFragmento(MensajesFragment())

        vi.homeTop.setContent {
            menuTopHome()
        }
        vi.homeMenu.setContent {
            menuHome()
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
    private fun menuTopHome() {
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
    private fun menuHome(isSelect:Boolean = false) {
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
                                }
                                items[1] -> {
                                    mostrarFragmento( ConfiguracionFragment() )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}