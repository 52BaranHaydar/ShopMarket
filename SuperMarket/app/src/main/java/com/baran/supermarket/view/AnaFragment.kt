package com.baran.supermarket.view

import Adapter.kategoriAdapter
import Adapter.yemekAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.baran.supermarket.R
import com.baran.supermarket.databinding.FragmentAnaBinding
import model.Kategori
import model.Yemek

class AnaFragment : Fragment() {
    private var _binding: FragmentAnaBinding? = null
    private val binding get() = _binding!!

    private lateinit var yemekListesi: ArrayList<Yemek>
    private lateinit var kategoriler: ArrayList<Kategori>
    private lateinit var yemekAdapter: yemekAdapter

    // Sepet için ViewModel (tüm activity’ye ortak olacak)
    private val sepetViewModel: SepetViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Yemek listesi
        yemekListesi = arrayListOf(
            Yemek("Şeftali", "15.00₺", R.drawable.seftali, "Meyveler"),
            Yemek("Avokado", "50.00₺", R.drawable.avakado, "Meyveler"),
            Yemek("Üzüm", "25.00₺", R.drawable.uzum, "Meyveler"),
            Yemek("Ananas", "70.00₺", R.drawable.ananas, "Meyveler"),
            Yemek("Nar", "25.00₺", R.drawable.nar, "Meyveler"),
            Yemek("Brokoli", "49.99₺", R.drawable.brocoli, "Sebzeler"),
            Yemek("Domates", "15.00₺", R.drawable.domates, "Sebzeler"),
            Yemek("Salatalık", "15.00₺", R.drawable.salatalik, "Sebzeler"),
            Yemek("Biber", "35.00₺", R.drawable.biber, "Sebzeler"),
            Yemek("Patlıcan", "25.00₺", R.drawable.patlican, "Sebzeler"),
            Yemek("Kola", "50.00₺", R.drawable.kola, "İçecekler"),
            Yemek("Ayran", "10.00₺", R.drawable.ayran, "İçecekler"),
            Yemek("Fanta", "15.00₺", R.drawable.fanta, "İçecekler"),
            Yemek("Su", "10.00₺", R.drawable.su, "İçecekler"),
            Yemek("Somun", "15.00₺", R.drawable.somun, "Ekmekler"),
            Yemek("Baget", "20.00₺", R.drawable.baget, "Ekmekler"),
            Yemek("Çavdar", "15.00₺", R.drawable.cavdar, "Ekmekler"),
            Yemek("Bazlama", "15.00₺", R.drawable.bazlama, "Ekmekler"),
            Yemek("Tam Buğday", "15.00₺", R.drawable.tambugday, "Ekmekler"),
            Yemek("Ayçiçek Yağı", "280.00₺", R.drawable.aycicek, "Yağlar"),
            Yemek("Mısır Yağı", "300.00₺", R.drawable.misir, "Yağlar"),
            Yemek("Susam Yağı", "320.00₺", R.drawable.susamyag, "Yağlar"),
            Yemek("Fındık Yağı", "250.00₺", R.drawable.findikyag, "Yağlar"),
            Yemek("Zeytinyağı", "200.00₺", R.drawable.zeytinyag, "Yağlar")
        )

        // Yemek adapteri
        yemekAdapter = yemekAdapter(ArrayList(yemekListesi),{ secilenYemek ->
            // Burada sepetViewModel’e ekle

        },{ secilenYemek ->
            sepetViewModel.sepeteEkle(secilenYemek)
            val action = AnaFragmentDirections.actionAnaFragmentToAlisverisFragment()
            Navigation.findNavController(view).navigate(action)
        })

        binding.yemekRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.yemekRecyclerView.adapter = yemekAdapter

        // Kategoriler
        kategoriler = arrayListOf(
            Kategori("Sebzeler", R.drawable.marul),
            Kategori("Meyveler", R.drawable.apple),
            Kategori("İçecekler", R.drawable.icecek),
            Kategori("Ekmekler", R.drawable.bread),
            Kategori("Yağlar", R.drawable.yag)
        )

        val kategoriAdapter = kategoriAdapter(kategoriler) { secilenKategori ->
            val filtrelenmis = yemekListesi.filter { it.kategori == secilenKategori.isim }
            yemekAdapter.updateList(ArrayList(filtrelenmis))
        }

        binding.kategorikRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.kategorikRecyclerView.adapter = kategoriAdapter

        binding.arama.doAfterTextChanged { editable ->
            val aramaMetni = editable.toString().trim().lowercase()
            val filtrelenmis = if (aramaMetni.isEmpty()) {
                yemekListesi
            } else {
                yemekListesi.filter { it.isim.lowercase().contains(aramaMetni) }
            }
            yemekAdapter.updateList(ArrayList(filtrelenmis))
        }

        binding.sepet.setOnClickListener {
            val action = AnaFragmentDirections.actionAnaFragmentToAlisverisFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}