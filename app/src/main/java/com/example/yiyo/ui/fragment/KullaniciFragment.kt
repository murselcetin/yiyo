package com.example.yiyo.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.yiyo.R
import com.example.yiyo.databinding.FragmentFavoriBinding
import com.example.yiyo.databinding.FragmentKullaniciBinding
import com.example.yiyo.ui.viewmodel.FavoriFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KullaniciFragment : Fragment() {
    private lateinit var binding: FragmentKullaniciBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKullaniciBinding.inflate(layoutInflater, container, false)

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavBar, navHostFragment.navController)

        binding.bottomNavBar.background = null
        binding.bottomNavBar.menu.getItem(1).isEnabled = false

        binding.fab.setOnClickListener {
            val modalBottomSheet = SepetFragment()
            modalBottomSheet.show(requireActivity().supportFragmentManager, "ModalBottomSheet")
        }

        binding.cardViewCikis.setOnClickListener {
            val builder = context?.let { it1 -> AlertDialog.Builder(it1) }
            val alertTasarim =
                LayoutInflater.from(context).inflate(R.layout.sepetten_sil_alert, null)
            val text = alertTasarim.findViewById(R.id.textViewAlertMesaj) as TextView
            val evetButton = alertTasarim.findViewById(R.id.buttonEvet) as Button
            val hayirButton = alertTasarim.findViewById(R.id.buttonHayir) as Button
            text.text = "Hesaptan çıkmak istiyor musunuz?"
            builder?.setView(alertTasarim)
            val d = builder?.create()
            evetButton.setOnClickListener {
                cikisYapildi()
                val action = KullaniciFragmentDirections.actionKullaniciFragmentToGirisFragment()
                findNavController().navigate(action)
                d?.dismiss()
            }
            hayirButton.setOnClickListener {
                d?.dismiss()
            }
            d?.show()
        }
        return binding.root
    }

    fun cikisYapildi() {
        val sharedPref = requireActivity().getSharedPreferences("giris", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("giris", false)
        editor.apply()
    }
}