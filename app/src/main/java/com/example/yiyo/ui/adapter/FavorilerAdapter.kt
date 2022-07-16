package com.example.yiyo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yiyo.R
import com.example.yiyo.data.entity.FavoriYemek
import com.example.yiyo.databinding.FavoriRecyclerviewBinding
import com.example.yiyo.ui.viewmodel.FavoriFragmentViewModel
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
    }

    override fun getItemCount(): Int {
        return favoriListesi.size
    }
}