package com.example.yiyo.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.yiyo.R
import com.example.yiyo.data.entity.FavoriYemek
import com.example.yiyo.databinding.FragmentYemekDetayBinding
import com.example.yiyo.ui.adapter.YemeklerAdapter
import com.example.yiyo.ui.viewmodel.AnasayfaFragmentViewModel
import com.example.yiyo.ui.viewmodel.YemekDetayFragmentViewModel
import com.example.yiyo.util.displayMetrics
import com.example.yiyo.util.resimYukle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class YemekDetayFragment : BottomSheetDialogFragment() {
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
        viewModel.yemekAdi = gelenYemek.yemek_adi
        binding.gelenYemek = gelenYemek


        viewModel.siparisYemekAdiGetir { itAdi->
            if(itAdi == gelenYemek.yemek_adi){
                viewModel.yemekAdetGetir { itAdet->
                    binding.yemekSiparisAdet.text = itAdet.toString()
                }
            }
        }

        binding.cardViewFavori.setOnClickListener{
            viewModel.favoriKayit(gelenYemek.yemek_resim_adi,gelenYemek.yemek_adi,gelenYemek.yemek_fiyat)
        }

        binding.imageViewYemek.resimYukle(gelenYemek.yemek_resim_adi)
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

    fun sepeteEkle(yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int, yemek_siparis_adet: Int) {
        viewModel.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, "mursel")
        val builder = context?.let { it1 -> AlertDialog.Builder(it1) }
        val alertTasarim = LayoutInflater.from(context).inflate(R.layout.siparis_tamamlandi_alert, null)
        val text = alertTasarim.findViewById(R.id.textViewOnaylandi) as TextView
        text.text = "Sepete Eklendi!"
        builder?.setView(alertTasarim)
        val d= builder?.create()
        d?.show()
        Handler().postDelayed({
            d?.dismiss()
        }, 1500)
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