package mx.unam.unaminternacional.dgecimovil.activitys

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.activitys.configuracion.ConfiguracionFragment
import mx.unam.unaminternacional.dgecimovil.activitys.creditos.CreditosFragment
import mx.unam.unaminternacional.dgecimovil.activitys.mensajes.MensajesFragment
import mx.unam.unaminternacional.dgecimovil.activitys.perfil.PerfilFragment
import mx.unam.unaminternacional.dgecimovil.databinding.ActivityHomeBinding
import mx.unam.unaminternacional.dgecimovil.modelviews.HomeViewModel
import mx.unam.unaminternacional.dgecimovil.trails.Constantes
import mx.unam.unaminternacional.dgecimovil.ui.DGECITheme


class Home : AppCompatActivity() {
    private lateinit var vi: ActivityHomeBinding

    private val mutable = MutableLiveData<Boolean>()
    private val mutableMenuLeft = MutableLiveData<Boolean>()

    private val HomeViewModel : HomeViewModel by viewModels()
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
        vi.homeLeft.setContent{
            MenuLeft()
        }

        //vi.homeLeft.z = 0F

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
                    //var expanded by remember { mutableStateOf(false) }
                    IconButton(onClick = {
                        mutableMenuLeft.postValue(true)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
                        )
                    }
                    /*
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
                    */
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
                    this@Home.mutable.observe(this@Home) {
                        if (!it) { selectedItem = -1  }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview
    private fun MenuLeft(){
        DGECITheme() {
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val items = listOf(Icons.Filled.Star)
            val itemsTxt = listOf("Creditos")
            val selectedItem = remember { mutableStateOf(items[0]) }

            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {



                    ModalDrawerSheet {
                        Spacer(Modifier.height(12.dp))
                        Image( painter = painterResource(id = R.drawable.icosvg),
                            contentDescription = "ACEPTAR",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(150.dp)
                                .clip(CircleShape)
                        )
                        Spacer(Modifier.height(12.dp))
                        items.forEachIndexed { index, item ->
                            NavigationDrawerItem(
                                icon = { Icon(item, contentDescription = null) },
                                label = { Text(itemsTxt[index]) },
                                selected = item == selectedItem.value,
                                onClick = {
                                    scope.launch {
                                        drawerState.close()
                                        mutableMenuLeft.postValue(false)
                                    }
                                    selectedItem.value = item
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                    }
                },
            content = {

            }
        )

            this@Home.mutableMenuLeft.observe(this@Home) {

                if (it) {
                    vi.homeLeft.z = 1F
                    scope.launch { drawerState.open() }
                } else {
                    vi.homeLeft.z = 0F
                }
            }
        }
    }
}