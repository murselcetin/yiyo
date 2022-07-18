package com.example.yiyo.ui.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.yiyo.R
import com.example.yiyo.data.entity.Kullanici
import com.example.yiyo.databinding.FragmentKayitBinding
import com.example.yiyo.ui.viewmodel.GirisFragmentViewModel
import com.example.yiyo.ui.viewmodel.KayitFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KayitFragment : Fragment() {
    private lateinit var binding: FragmentKayitBinding
    private lateinit var viewModel: KayitFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_kayit, container, false)
        binding.kayitFragment=this

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: KayitFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun kullaniciKayit(kullaniciAdi: String, sifre: String) {
        viewModel.kullaniciKaydet( kullaniciAdi, sifre)
        Snackbar.make(binding.buttonUyeOl, "Üye Oluşturuldu", Snackbar.LENGTH_SHORT).show()
    }
}