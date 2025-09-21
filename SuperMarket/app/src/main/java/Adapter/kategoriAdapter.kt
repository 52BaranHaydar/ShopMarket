package Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baran.supermarket.databinding.KategoriRowBinding
import model.Kategori

class kategoriAdapter(var kategoriSayısı : ArrayList<Kategori>,
                      private val onItemClick: (Kategori) -> Unit) : RecyclerView.Adapter<kategoriAdapter.kategoriViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kategoriViewHolder {
        val binding = KategoriRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return kategoriViewHolder(binding)
    }

    override fun onBindViewHolder(holder: kategoriViewHolder, position: Int
    ) {
        val kategori = kategoriSayısı[position]

        holder.binding.isim.text = kategori.isim
        holder.binding.resim.setImageResource(kategori.gorsel)

        holder.itemView.setOnClickListener {
            onItemClick(kategori)
        }

    }

    override fun getItemCount(): Int {
        return kategoriSayısı.size
    }

    class kategoriViewHolder(var binding : KategoriRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}