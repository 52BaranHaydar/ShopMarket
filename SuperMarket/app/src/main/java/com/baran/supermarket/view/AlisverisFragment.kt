package com.baran.supermarket.view

import Adapter.SepetAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.baran.supermarket.databinding.FragmentAlisverisBinding

class AlisverisFragment : Fragment() {

    private var _binding: FragmentAlisverisBinding? = null
    private val binding get() = _binding!!

    private val sepetViewModel: SepetViewModel by activityViewModels()
    private lateinit var adapter: SepetAdapter // Adapter değişkeni ekledik

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlisverisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1️⃣ Adapteri oluştur
        adapter = SepetAdapter(arrayListOf(),{ yemek ->
            sepetViewModel.sepettenCikar(yemek)
        },{toplam ->
            binding.deger.text = " %.2f ₺".format(toplam)


        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        // 2️⃣ LiveData'yı gözlemle
        sepetViewModel.sepetListesi.observe(viewLifecycleOwner) { liste ->
            adapter.updateList(ArrayList(liste)) // Liste değişirse RecyclerView güncellenir
        }

        binding.geri.setOnClickListener {
            val action  = AlisverisFragmentDirections.actionAlisverisFragmentToAnaFragment()
            Navigation.findNavController(view).navigate(action)
        }

        binding.git.setOnClickListener {
            val action = AlisverisFragmentDirections.actionAlisverisFragmentToOdemeFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}