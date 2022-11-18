package mx.unam.unaminternacional.dgecimovil.activitys.auth

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.activitys.Home
import mx.unam.unaminternacional.dgecimovil.databinding.FragmentInstruccionesBinding
import mx.unam.unaminternacional.dgecimovil.ui.theme.DGECITheme
import mx.unam.unaminternacional.dgecimovil.ui.theme.Xbb
import mx.unam.unaminternacional.dgecimovil.ui.theme.XdI

class AuthInstruccionesFragment : Fragment() {
    private var _binding: FragmentInstruccionesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInstruccionesBinding.inflate(inflater, container, false)
        val view = binding.root

        val spannable = SpannableStringBuilder(getString(R.string.mensaje_instrucciones))
        spannable.setSpan(RelativeSizeSpan(1.30f), 0, spannable.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.mensajeInstrucciones.text = spannable
        binding.mensajeInstrucciones.movementMethod = LinkMovementMethod.getInstance()


        binding.siguiente2.setContent {
            btn_siguiente()
        }
        return view
    }

    @Preview(name = "boton")
    @Composable
    private fun btn_siguiente() {
        DGECITheme() {
            Button(
                onClick = {
                    val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
                    viewPager?.currentItem = 2
                },
                modifier = Modifier.padding(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = XdI
                )
            ) {
                Text(
                    text = getString(R.string.siguiente),
                    fontSize = 20.sp
                )
                Icon(imageVector = Icons.Filled.PlayArrow, getString(R.string.siguiente))
            }
        }

    }
}