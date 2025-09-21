package Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baran.supermarket.databinding.SepetrecyclerRowBinding
import model.Yemek

class SepetAdapter(
    private val yemekListesi: ArrayList<Yemek>,
    private val onSilClick: (Yemek) -> Unit,
    private val onToplamDegisti: (Double) -> Unit
) : RecyclerView.Adapter<SepetAdapter.SepetHolder>() {

    class SepetHolder(val binding: SepetrecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetHolder {
        val binding = SepetrecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SepetHolder(binding)
    }

    override fun onBindViewHolder(holder: SepetHolder, position: Int) {
        val yemek = yemekListesi[position]

        holder.binding.isim.text = yemek.isim
        holder.binding.fiyat.text = yemek.fiyat
        holder.binding.gorsel.setImageResource(yemek.gorsel)

        val rowFiyat = yemek.fiyat.replace("₺", "").trim().toDoubleOrNull() ?: 0.0
        var adet = 1

        fun guncelleToplam() {
            var toplam = 0.0
            for (i in yemekListesi.indices) {
                val fiyat = yemekListesi[i].fiyat.replace("₺", "").trim().toDoubleOrNull() ?: 0.0
                toplam += fiyat * if (i == holder.adapterPosition) adet else 1
            }
            onToplamDegisti(toplam)
        }

        holder.binding.topla.setOnClickListener {
            adet++
            holder.binding.bilgi.text = adet.toString()
            guncelleToplam()
        }

        holder.binding.eksi.setOnClickListener {
            val currentPos = holder.adapterPosition
            if (currentPos == RecyclerView.NO_POSITION || currentPos >= yemekListesi.size) return@setOnClickListener

            if (adet >= 1) {
                adet--
                holder.binding.bilgi.text = adet.toString()
            } else {
                val silinecekYemek = yemekListesi[currentPos]
                yemekListesi.removeAt(currentPos)
                notifyItemRemoved(currentPos)
                guncelleToplam()
                return@setOnClickListener
            }
            guncelleToplam()
        }

        holder.binding.sil.setOnClickListener {
            val currentPos = holder.adapterPosition
            if (currentPos == RecyclerView.NO_POSITION || currentPos >= yemekListesi.size) return@setOnClickListener

            val silinecekYemek = yemekListesi[currentPos]
            yemekListesi.removeAt(currentPos)
            notifyItemRemoved(currentPos)
            onSilClick(silinecekYemek)
            guncelleToplam()
        }

        guncelleToplam()
    }

    override fun getItemCount(): Int = yemekListesi.size

    fun updateList(yeniListe: ArrayList<Yemek>) {
        yemekListesi.clear()
        yemekListesi.addAll(yeniListe)
        notifyDataSetChanged()
        var toplam = 0.0
        for (y in yemekListesi) {
            toplam += y.fiyat.replace("₺", "").trim().toDoubleOrNull() ?: 0.0
        }
        onToplamDegisti(toplam)
    }
}