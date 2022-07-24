package com.example.yiyo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yiyo.R
import com.example.yiyo.data.entity.SepetYemekler
import com.example.yiyo.databinding.SepetYemekRecyclerviewBinding
import com.example.yiyo.ui.viewmodel.SepetFragmentViewModel
import com.example.yiyo.util.resimYukle


class SepetYemeklerAdapter(var mContext: Context, var sepettekiYemekListesi: List<SepetYemekler>, var viewModel: SepetFragmentViewModel,var kullaniciAdi:String) : RecyclerView.Adapter<SepetYemeklerAdapter.TasarimTutucu>() {
    inner class TasarimTutucu(binding: SepetYemekRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: SepetYemekRecyclerviewBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: SepetYemekRecyclerviewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.sepet_yemek_recyclerview, parent,
                false
            )
        return TasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: TasarimTutucu, position: Int) {
        val sepettekiYemek = sepettekiYemekListesi[position]
        val t = holder.binding
        t.sepetYemek = sepettekiYemek

        t.yemekResim.resimYukle(sepettekiYemek.yemek_resim_adi)
        t.imageViewSil.setOnClickListener {
            val builder = AlertDialog.Builder(mContext)
            val alertTasarim = LayoutInflater.from(mContext).inflate(R.layout.sepetten_sil_alert, null)
            val evetButton = alertTasarim.findViewById(R.id.buttonEvet) as Button
            val hayirButton = alertTasarim.findViewById(R.id.buttonHayir) as Button
            builder.setView(alertTasarim)
            val d= builder.create()
            evetButton.setOnClickListener {
                viewModel.sepettekiYemekSil(sepettekiYemek.sepet_yemek_id, sepettekiYemek.kullanici_adi)
                viewModel.sepettekiYemekleriYukle(kullaniciAdi)
                d.dismiss()
            }
            hayirButton.setOnClickListener {
                d.dismiss()
            }
            d.show()
        }
    }

    override fun getItemCount(): Int {
        return sepettekiYemekListesi.size
    }

}