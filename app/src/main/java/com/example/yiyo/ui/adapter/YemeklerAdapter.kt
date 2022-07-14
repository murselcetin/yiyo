package com.example.yiyo.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.yiyo.R
import com.example.yiyo.data.entity.Yemekler
import com.example.yiyo.databinding.YemekRecyclerviewBinding
import com.example.yiyo.ui.fragment.AnasayfaFragmentDirections
import com.example.yiyo.ui.viewmodel.AnasayfaFragmentViewModel
import com.example.yiyo.util.gecisYap
import com.example.yiyo.util.resimYukle


class YemeklerAdapter(
    var mContext: Context,
    var yemeklerListesi: List<Yemekler>,
    var viewModel: AnasayfaFragmentViewModel
) : RecyclerView.Adapter<YemeklerAdapter.TasarimTutucu>() {
    inner class TasarimTutucu(binding: YemekRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: YemekRecyclerviewBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: YemekRecyclerviewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.yemek_recyclerview, parent, false)
        return TasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: TasarimTutucu, position: Int) {
        val yemek = yemeklerListesi[position]
        val t = holder.binding
        t.yemek = yemek

        t.imageViewYemekResim.resimYukle(yemek.yemek_resim_adi)
        t.cardViewYemek.setOnClickListener() {
            val gecis = AnasayfaFragmentDirections.anasayfaToYemekDetay(yemek = yemek)
            Navigation.gecisYap(it, gecis)
        }
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

}