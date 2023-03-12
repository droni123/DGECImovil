package mx.unam.unaminternacional.dgecimovil.activitys.auth

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.databinding.FragmentInstruccionesBinding
import mx.unam.unaminternacional.dgecimovil.ui.DGECITheme
import mx.unam.unaminternacional.dgecimovil.ui.Xbb
import mx.unam.unaminternacional.dgecimovil.ui.XdI

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
            ElevatedButton(
                onClick = {
                    val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
                    viewPager?.currentItem = 2
                },
                modifier = Modifier.padding(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = XdI,
                    contentColor = Xbb
                )
            ) {
                Text(
                    text = context?.let{ getString(R.string.siguiente)} ?: run { "" },
                    fontSize = 20.sp
                )
                Icon(imageVector = Icons.Filled.PlayArrow, context?.let{ getString(R.string.siguiente)} ?: run { "" })
            }
        }
    }
}