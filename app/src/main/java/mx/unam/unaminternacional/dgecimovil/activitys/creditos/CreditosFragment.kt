package mx.unam.unaminternacional.dgecimovil.activitys.creditos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.unam.unaminternacional.dgecimovil.R
import mx.unam.unaminternacional.dgecimovil.databinding.FragmentCreditosBinding
import mx.unam.unaminternacional.dgecimovil.databinding.FragmentMensajesBinding

class CreditosFragment : Fragment() {
    private var _binding: FragmentCreditosBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreditosBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }
}