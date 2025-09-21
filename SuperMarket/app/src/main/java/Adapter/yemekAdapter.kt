package Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baran.supermarket.databinding.RecyclerRowBinding
import model.Kategori

import model.Yemek

class yemekAdapter(
    private val yemekListesi : ArrayList<Yemek>,
    private val onItemClick: (Yemek) -> Unit,
    private val onSepeteEkle: (Yemek) -> Unit
) : RecyclerView.Adapter<yemekAdapter.yemekHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): yemekHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return yemekHolder(binding)
    }

    override fun onBindViewHolder(holder: yemekHolder, position: Int) {
        val yemek = yemekListesi[position]

        holder.binding.isim.text = yemek.isim
        holder.binding.fiyat.text = yemek.fiyat
        holder.binding.gorsel.setImageResource(yemek.gorsel)

        holder.itemView.setOnClickListener {
            onItemClick(yemek)
        }

        holder.binding.ekle.setOnClickListener {
            onSepeteEkle(yemek)
        }

    }

    override fun getItemCount(): Int {
        return yemekListesi.size
    }
    fun updateList(yeniListe: ArrayList<Yemek>) {
        yemekListesi.clear()
        yemekListesi.addAll(yeniListe)
        notifyDataSetChanged()
    }

    class yemekHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){
        val ekle = binding.ekle

    }
}