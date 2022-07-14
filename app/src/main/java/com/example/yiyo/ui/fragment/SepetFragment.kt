package com.example.yiyo.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.yiyo.R
import com.example.yiyo.databinding.FragmentSepetBinding
import com.example.yiyo.ui.adapter.SepetYemeklerAdapter
import com.example.yiyo.ui.viewmodel.SepetFragmentViewModel
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SepetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSepetBinding
    private lateinit var viewModel: SepetFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)
        binding.sepetFragment = this

        viewModel.sepettekiYemekListesi.observe(viewLifecycleOwner) {
            val adapter = SepetYemeklerAdapter(requireContext(), it, viewModel)
            Log.e("AdapterSayi",it.toString())
            binding.sepetYemeklerAdapter = adapter
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
        viewModel.sepettekiYemekleriYukle("mursel")
    }
}