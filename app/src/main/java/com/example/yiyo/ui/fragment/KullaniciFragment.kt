package com.example.yiyo.ui.fragment

import android.app.Dialog
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
import com.example.yiyo.util.MySharedPreferences
import com.example.yiyo.util.displayMetrics
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class KullaniciFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentKullaniciBinding
    @Inject
    lateinit var prefs : MySharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKullaniciBinding.inflate(layoutInflater, container, false)

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
                val action = KullaniciFragmentDirections.actionKullaniciFragmentToGirisFragment()
                findNavController().navigate(action)
                cikisYapildi()
                d?.dismiss()
            }
            hayirButton.setOnClickListener {
                d?.dismiss()
            }
            d?.show()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textViewKullaniciAdi.text = prefs.kullaniciAdi
        super.onViewCreated(view, savedInstanceState)
    }

    fun cikisYapildi() {
        prefs.girisKontrol = true
        prefs.kullaniciAdi = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        activity?.displayMetrics()?.run {
            val height = heightPixels*0.9
            val layoutParams = bottomSheet.layoutParams
            layoutParams.height = height.toInt()
            bottomSheet.layoutParams = layoutParams
        }
    }
}