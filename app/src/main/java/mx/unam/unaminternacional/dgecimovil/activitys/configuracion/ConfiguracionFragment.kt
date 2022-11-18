package mx.unam.unaminternacional.dgecimovil.activitys.configuracion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import mx.unam.unaminternacional.dgecimovil.databinding.FragmentConfiguracionBinding

class ConfiguracionFragment : Fragment() {
    private var _binding: FragmentConfiguracionBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfiguracionBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }
}