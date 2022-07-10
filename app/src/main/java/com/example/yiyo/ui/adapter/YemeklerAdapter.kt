package com.example.yiyo.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yiyo.R
import com.example.yiyo.data.entity.Yemekler
import com.example.yiyo.databinding.YemekRecyclerviewBinding
import com.example.yiyo.ui.viewmodel.AnasayfaFragmentViewModel


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
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

}