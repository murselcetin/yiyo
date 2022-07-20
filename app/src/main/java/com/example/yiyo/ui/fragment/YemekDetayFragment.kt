package com.example.yiyo.ui.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
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
import com.example.yiyo.databinding.FragmentYemekDetayBinding
import com.example.yiyo.ui.viewmodel.FavoriFragmentViewModel
import com.example.yiyo.ui.viewmodel.YemekDetayFragmentViewModel
import com.example.yiyo.util.MySharedPreferences
import com.example.yiyo.util.displayMetrics
import com.example.yiyo.util.resimYukle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class YemekDetayFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentYemekDetayBinding
    private lateinit var viewModel: YemekDetayFragmentViewModel
    @Inject
    lateinit var prefs : MySharedPreferences
    var isFavoriteStateChange: Boolean = false
    companion object {
        const val IS_FAVORITE_STATE_CHANGE = "is_favorite_state_change"
    }

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

        viewModel.favoriKontrol(gelenYemek.yemek_adi)
        viewModel.favoriKontrol.observe(viewLifecycleOwner){
            favoriResimDegis(it)
        }
        binding.cardViewFavori.setOnClickListener{
            isFavoriteStateChange = true
            if(viewModel.favoriKontrol.value==true)
            {
                viewModel.favoriId(gelenYemek.yemek_adi)
                viewModel.favoriId.observe(viewLifecycleOwner){
                    var favoriId = it
                    favoriResimDegis(false)
                    viewModel.favoriSil(favoriId,gelenYemek.yemek_resim_adi,gelenYemek.yemek_adi,gelenYemek.yemek_fiyat)
                }
            }else{
                favoriResimDegis(true)
                viewModel.favoriKayit(gelenYemek.yemek_resim_adi,gelenYemek.yemek_adi,gelenYemek.yemek_fiyat)
            }
        }
        binding.imageViewYemek.resimYukle(gelenYemek.yemek_resim_adi)
        return binding.root
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            IS_FAVORITE_STATE_CHANGE,
            isFavoriteStateChange
        )
        findNavController().popBackStack()
    }

    fun favoriResimDegis(kontrol:Boolean){
        if(kontrol){
            binding.favoriImage.setImageResource(R.drawable.favori_dolu)
        }else{
            binding.favoriImage.setImageResource(R.drawable.favori_bos)
        }
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
        viewModel.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, prefs.kullaniciAdi?:"")
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