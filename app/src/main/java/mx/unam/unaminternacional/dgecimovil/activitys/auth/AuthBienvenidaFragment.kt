package mx.unam.unaminternacional.dgecimovil.activitys.auth

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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.databinding.FragmentMensajeBienvenidaBinding
import mx.unam.unaminternacional.dgecimovil.trails.AppDgeciMovil
import mx.unam.unaminternacional.dgecimovil.ui.DGECITheme
import mx.unam.unaminternacional.dgecimovil.ui.Xbb
import mx.unam.unaminternacional.dgecimovil.ui.XdI

class AuthBienvenidaFragment : Fragment() {
    private var _binding: FragmentMensajeBienvenidaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View? {
        _binding = FragmentMensajeBienvenidaBinding.inflate(inflater, container, false)
        val view = binding.root

        val url = getString(R.string.url_dgeci)
        val color = ContextCompat.getColor(AppDgeciMovil.instance, R.color.warning)
        val spannable = SpannableStringBuilder(getString(R.string.mensaje_bienvenida))
        spannable.setSpan(StyleSpan(Typeface.BOLD), 0, 21, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        spannable.setSpan(RelativeSizeSpan(1.80f), 0, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(RelativeSizeSpan(1.30f), 27, spannable.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(ForegroundColorSpan( color ), 107, spannable.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(URLSpan(url),107,spannable.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.mensajeBienvenida.text = spannable
        binding.mensajeBienvenida.movementMethod = LinkMovementMethod.getInstance()
        binding.siguiente.setContent {
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
                    viewPager?.currentItem = 1
                },
                modifier = Modifier.padding(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = XdI,
                    contentColor = Xbb
                )
            ) {
                Text(
                    text = stringResource(R.string.siguiente),
                    fontSize = 20.sp
                )
                Icon(imageVector = Icons.Filled.PlayArrow, stringResource(R.string.siguiente))
            }
        }

    }

}