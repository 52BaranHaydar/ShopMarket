package com.baran.supermarket.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.baran.supermarket.databinding.FragmentOdemeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private var _binding: FragmentOdemeBinding? = null
private val binding get() = _binding!!
private lateinit var db: FirebaseFirestore


class OdemeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = FirebaseFirestore.getInstance() // KTX yok, klasik yöntem
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOdemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ana.setOnClickListener {
            val action = OdemeFragmentDirections.actionOdemeFragmentToAlisverisFragment()
            Navigation.findNavController(view).navigate(action)
        }

        binding.ode.setOnClickListener {

            val ad = binding.ad.text.toString()
            val number = binding.number.text.toString()
            val tarih = binding.tarih.text.toString()
            val ccv = binding.CCV.text.toString()

            if (ad.isBlank() || number.isBlank() || tarih.isBlank() || ccv.isBlank()) {
                Toast.makeText(requireContext(), "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = hashMapOf(
                "ad" to ad,
                "number" to number,
                "tarih" to tarih,
                "ccv" to ccv
            )

            db.collection("Ödeme").add(data)
                .addOnSuccessListener {
                    val action = OdemeFragmentDirections.actionOdemeFragmentToTamamlamaFragment()
                    Navigation.findNavController(view).navigate(action)
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_LONG).show()
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}