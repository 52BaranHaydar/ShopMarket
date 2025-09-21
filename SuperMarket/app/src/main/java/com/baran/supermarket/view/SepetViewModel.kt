package com.baran.supermarket.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.Yemek

class SepetViewModel : ViewModel() {

    // Sepetteki ürünleri tutan liste
    private val _sepetListesi = MutableLiveData<ArrayList<Yemek>>(arrayListOf())
    val sepetListesi: LiveData<ArrayList<Yemek>> get() = _sepetListesi

    // Sepete yemek ekleme
    fun sepeteEkle(yemek: Yemek) {
        val guncelListe = _sepetListesi.value ?: arrayListOf()
        guncelListe.add(yemek)
        _sepetListesi.value = guncelListe
    }

    // Sepetten yemek silme
    fun sepettenCikar(yemek: Yemek) {
        val guncelListe = _sepetListesi.value ?: arrayListOf()
        guncelListe.remove(yemek)
        _sepetListesi.value = guncelListe
    }

    // Sepeti tamamen boşaltma
    fun sepetiTemizle() {
        _sepetListesi.value = arrayListOf()
    }
}
