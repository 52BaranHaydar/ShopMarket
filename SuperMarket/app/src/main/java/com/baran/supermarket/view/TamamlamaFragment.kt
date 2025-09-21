package com.baran.supermarket.view

import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.baran.supermarket.R
import com.baran.supermarket.databinding.FragmentTamamlamaBinding

private var _binding: FragmentTamamlamaBinding? = null
// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!
class TamamlamaFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTamamlamaBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.anaEkran.setOnClickListener {
            val action = TamamlamaFragmentDirections.actionTamamlamaFragmentToAlisverisFragment()
            Navigation.findNavController(view).navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}