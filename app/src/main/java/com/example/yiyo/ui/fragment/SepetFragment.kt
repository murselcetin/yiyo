package com.example.yiyo.ui.fragment

import android.app.Dialog
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.yiyo.R
import com.example.yiyo.databinding.FragmentSepetBinding
import com.example.yiyo.ui.adapter.SepetYemeklerAdapter
import com.example.yiyo.ui.viewmodel.SepetFragmentViewModel
import com.example.yiyo.util.MySharedPreferences
import com.example.yiyo.util.displayMetrics

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SepetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSepetBinding
    private lateinit var viewModel: SepetFragmentViewModel
    @Inject
    lateinit var prefs: MySharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)
        binding.sepetFragment = this

        viewModel.sepettekiYemekListesi.observe(viewLifecycleOwner) {
            val adapter =
                SepetYemeklerAdapter(requireContext(), it, viewModel, prefs.kullaniciAdi ?: "")
            Log.e("AdapterSayi", it.toString())
            binding.sepetYemeklerAdapter = adapter
        }
        viewModel.sepettekiYemekListesi.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.rv.visibility = View.INVISIBLE
                binding.sepetOnay.visibility = View.GONE
                binding.anim.visibility = View.VISIBLE
                binding.sepetBos.visibility = View.VISIBLE
            } else {
                binding.rv.visibility = View.VISIBLE
                binding.sepetOnay.visibility = View.VISIBLE
                binding.anim.visibility = View.INVISIBLE
                binding.sepetBos.visibility = View.INVISIBLE
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepettekiYemekleriYukle(prefs.kullaniciAdi ?: "")
    }

    fun sepetOnayla() {
        val sepetonay = SepetOnayFragment()
        val bundle = Bundle()
        bundle.putDouble("tutar", viewModel.getYemekTutar())
        bundle.putIntegerArrayList("yemekid", viewModel.getYemekIdList())
        sepetonay.arguments = bundle
        sepetonay.show(childFragmentManager, "sepetData")
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
            val height = heightPixels * 0.9
            val layoutParams = bottomSheet.layoutParams
            layoutParams.height = height.toInt()
            bottomSheet.layoutParams = layoutParams
        }

    }
}