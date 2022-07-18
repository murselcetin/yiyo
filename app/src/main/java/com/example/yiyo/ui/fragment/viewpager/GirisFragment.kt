package com.example.yiyo.ui.fragment.viewpager

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.yiyo.R
import com.example.yiyo.data.entity.Kullanici
import com.example.yiyo.databinding.FragmentGirisBinding
import com.example.yiyo.ui.viewmodel.GirisFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GirisFragment : Fragment() {
    private lateinit var binding: FragmentGirisBinding
    private lateinit var viewModel: GirisFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_giris,container,false)
        binding.girisFragment=this

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: GirisFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(giris())
        {
            val action = GirisFragmentDirections.actionGirisFragmentToAnasayfaFragment()
            findNavController().navigate(action)
        }else{
            viewModel.kullaniciListesi.observe(viewLifecycleOwner) {
                it.getOrNull(0)?.let {
                    girisYapildi()
                    val action = GirisFragmentDirections.actionGirisFragmentToAnasayfaFragment()
                    findNavController().navigate(action)
                } ?: run {
                    Toast.makeText(requireContext(), "GİRİŞ YAPILAMADI", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun kayıtGecis(){
        val action = GirisFragmentDirections.actionGirisFragmentToKayitFragment2()
        findNavController().navigate(action)
    }

    fun loginKontrol() {
        val username = binding.kullaniciAdi.text.toString().trim()
        val password = binding.sifre.text.toString().trim()
        if (!username.isNullOrEmpty() && !password.isNullOrEmpty()) {
            viewModel.kullaniciKontrol(Kullanici(kullanici_adi = username, sifre = password))
        }
    }

    fun giris(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("giris", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("giris",false)
    }

    fun girisYapildi(){
        val aktifKullaniciAdi = requireActivity().getSharedPreferences("kullaniciadi", Context.MODE_PRIVATE)
        val sharedPref = requireActivity().getSharedPreferences("giris", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val kEditor = aktifKullaniciAdi.edit()
        editor.putBoolean("giris",true)
        kEditor.putString("kullaniciadi",binding.kullaniciAdi.text.toString())
        editor.apply()
        kEditor.apply()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                        activity?.finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }
}