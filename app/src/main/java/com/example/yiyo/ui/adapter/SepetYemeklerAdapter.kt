package com.example.yiyo.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yiyo.R
import com.example.yiyo.data.entity.SepetYemekler
import com.example.yiyo.databinding.ActivityMainBinding.inflate
import com.example.yiyo.databinding.AlertDialogBinding
import com.example.yiyo.databinding.FragmentSepetBinding
import com.example.yiyo.databinding.SepetYemekRecyclerviewBinding
import com.example.yiyo.databinding.YemekRecyclerviewBinding
import com.example.yiyo.ui.fragment.SepetFragment
import com.example.yiyo.ui.viewmodel.SepetFragmentViewModel


class SepetYemeklerAdapter(
    var mContext: Context,
    var sepettekiYemekListesi: List<SepetYemekler>,
    var viewModel: SepetFragmentViewModel
) : RecyclerView.Adapter<SepetYemeklerAdapter.TasarimTutucu>() {
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
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.sepet_yemek_recyclerview,
                parent,
                false
            )
        return TasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: TasarimTutucu, position: Int) {
        val sepettekiYemek = sepettekiYemekListesi[position]
        val t = holder.binding
        t.sepetYemek = sepettekiYemek

        t.cardViewSepetYemek.setOnClickListener {
            val builder = AlertDialog.Builder(mContext)
            builder.setTitle("YİYO")
            builder.setMessage("Seçtiğiniz yemeği sepetten çıkartmak istediğinize emin misiniz ?")

            builder.setPositiveButton("Evet") { dialog, which ->
                viewModel.sepettekiYemekSil(sepettekiYemek.sepet_yemek_id, sepettekiYemek.kullanici_adi)
                viewModel.sepettekiYemekleriYukle("mursel")
            }
            builder.setNegativeButton("Hayır") { dialog, which -> }

            builder.show()
        }
    }

    override fun getItemCount(): Int {
        return sepettekiYemekListesi.size
    }

}