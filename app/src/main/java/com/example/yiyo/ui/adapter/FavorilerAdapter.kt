package com.example.yiyo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yiyo.R
import com.example.yiyo.data.entity.FavoriYemek
import com.example.yiyo.data.entity.Yemekler
import com.example.yiyo.databinding.FavoriRecyclerviewBinding
import com.example.yiyo.ui.fragment.AnasayfaFragmentDirections
import com.example.yiyo.ui.fragment.FavoriFragment
import com.example.yiyo.ui.fragment.FavoriFragmentDirections
import com.example.yiyo.ui.viewmodel.FavoriFragmentViewModel
import com.example.yiyo.util.gecisYap
import com.example.yiyo.util.resimYukle

class FavorilerAdapter(var mContext: Context, var favoriListesi: List<FavoriYemek>, var viewModel: FavoriFragmentViewModel) : RecyclerView.Adapter<FavorilerAdapter.TasarimTutucu>() {
    inner class TasarimTutucu(binding: FavoriRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: FavoriRecyclerviewBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: FavoriRecyclerviewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.favori_recyclerview, parent, false)
        return TasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: TasarimTutucu, position: Int) {
        val favori = favoriListesi[position]
        val t = holder.binding
        t.favori = favori
        t.imageViewYemekResim.resimYukle(favori.yemek_resim_adi)
        val fyemek = favori.toYemekler()
        t.cardViewYemek.setOnClickListener() {
            val gecis = FavoriFragmentDirections.favoriToYemekDetay(yemek = fyemek)
            Navigation.gecisYap(it, gecis)
        }
    }

    fun FavoriYemek.toYemekler() = Yemekler(
        yemek_id = 0,
        yemek_adi = yemek_adi,
        yemek_resim_adi = yemek_resim_adi,
        yemek_fiyat = yemek_fiyat
    )

    override fun getItemCount(): Int {
        return favoriListesi.size
    }
}