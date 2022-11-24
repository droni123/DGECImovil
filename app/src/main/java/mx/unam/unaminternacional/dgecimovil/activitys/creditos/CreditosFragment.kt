package mx.unam.unaminternacional.dgecimovil.activitys.creditos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.ibrahimsn.particle.ParticleView
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.databinding.FragmentCreditosBinding
import mx.unam.unaminternacional.dgecimovil.trails.StringToHtml
import mx.unam.unaminternacional.dgecimovil.ui.DGECITheme
import mx.unam.unaminternacional.dgecimovil.ui.Xbb

class CreditosFragment : Fragment() {
    private var _binding: FragmentCreditosBinding? = null
    private val binding get() = _binding!!
    private lateinit var particleView: ParticleView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreditosBinding.inflate(inflater, container, false)
        val view = binding.root
        particleView = binding.particleView
        // Inflate the layout for this fragment
        binding.creditosRoot.setContent{
            Creditos()
        }
        return view
    }

    override fun onPause() {
        super.onPause()
        particleView.pause()
        particleView.resume()
    }

    override fun onResume() {
        super.onResume()

    }
    @Composable
    @Preview
    fun Creditos(){
        DGECITheme(
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.unam_blanco),
                    contentDescription = getString(R.string.unam))
                Text(
                    text = getString(R.string.creditos_p1),
                    textAlign = TextAlign.Center,
                    color = Xbb,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),
                    fontWeight = FontWeight.Light,
                    lineHeight = 35.sp
                )
                Text(
                    text = StringToHtml.parseHtml(getString(R.string.creditos_p2)),
                    textAlign = TextAlign.Center,
                    color = Xbb,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = getString(R.string.creditos_p3),
                    textAlign = TextAlign.Center,
                    color = Xbb,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp),
                    fontWeight = FontWeight.Light,
                    lineHeight = 35.sp
                )
                Text(
                    text = StringToHtml.parseHtml(getString(R.string.creditos_p4)),
                    textAlign = TextAlign.Center,
                    color = Xbb,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = StringToHtml.parseHtml(getString(R.string.creditos_p5)),
                    textAlign = TextAlign.Center,
                    color = Xbb,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    fontWeight = FontWeight.Light
                )
            }
        }
    }





}