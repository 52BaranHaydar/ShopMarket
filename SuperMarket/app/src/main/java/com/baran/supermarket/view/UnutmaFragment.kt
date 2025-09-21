package com.baran.supermarket.view

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.baran.supermarket.R
import com.baran.supermarket.databinding.FragmentUnutmaBinding
import com.google.firebase.auth.FirebaseAuth

private var _binding: FragmentUnutmaBinding? = null
private val binding get() = _binding!!

class UnutmaFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUnutmaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.degisme.setOnClickListener {
            val email = binding.email.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Lütfen email girin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Firebase password reset email gönder
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Şifre sıfırlama emaili gönderildi", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(requireContext(), "Email gönderilemedi: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }

            val action = UnutmaFragmentDirections.actionUnutmaFragmentToLoginFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}