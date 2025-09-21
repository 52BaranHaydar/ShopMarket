package com.baran.supermarket.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.baran.supermarket.R
import com.baran.supermarket.databinding.FragmentGirisBinding


class GirisFragment : Fragment() {
    private var _binding: FragmentGirisBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGirisBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.devamet.setOnClickListener {
            val actiion = GirisFragmentDirections.actionGirisFragmentToWelcomeFragment()
            Navigation.findNavController(view).navigate(actiion)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

