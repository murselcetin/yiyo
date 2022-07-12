package com.example.yiyo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.yiyo.R
import com.example.yiyo.databinding.FragmentYemekDetayBinding
import com.example.yiyo.ui.viewmodel.AnasayfaFragmentViewModel
import com.example.yiyo.ui.viewmodel.YemekDetayFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemekDetayFragment : Fragment() {
    private lateinit var binding: FragmentYemekDetayBinding
    private lateinit var viewModel: YemekDetayFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_yemek_detay, container, false)
        binding.yemekDetayFragment = this

        val bundle: YemekDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek
        binding.gelenYemek = gelenYemek

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: YemekDetayFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun yemekAdetArti() {
        var yemekAdet = Integer.parseInt(binding.yemekSiparisAdet.text.toString())
        binding.yemekSiparisAdet.text = (yemekAdet + 1).toString()
    }

    fun yemekAdetEksi() {
        var yemekAdet = Integer.parseInt(binding.yemekSiparisAdet.text.toString())
        binding.yemekSiparisAdet.text = (yemekAdet - 1).toString()
    }

    fun sepeteEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int) {
        viewModel.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, "mursel")
    }
}