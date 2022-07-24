package com.example.yiyo.ui.fragment

import android.app.Dialog

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.yiyo.R
import com.example.yiyo.databinding.FragmentSepetOnayBinding
import com.example.yiyo.ui.adapter.SepetYemeklerAdapter
import com.example.yiyo.ui.viewmodel.SepetFragmentViewModel
import com.example.yiyo.ui.viewmodel.SepetOnayFragmentViewModel
import com.example.yiyo.util.MySharedPreferences
import com.example.yiyo.util.convert
import com.example.yiyo.util.displayMetrics

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SepetOnayFragment : BottomSheetDialogFragment(), OnMapReadyCallback {

    private lateinit var viewModel: SepetOnayFragmentViewModel
    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentSepetOnayBinding
    @Inject
    lateinit var prefs: MySharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSepetOnayBinding.inflate(inflater, container, false)

        val tutar = arguments?.getDouble("tutar")
        val sepetIdList = arguments?.getIntegerArrayList("yemekid")
        sepetIdList?.let {
          viewModel.sepetIdList = it
        }

        tutar?.let {
            binding.textViewTutar.text = tutar.convert()
            binding.textViewOdenecekTutar.text =tutar.convert()
        }


        binding.buttonOnay.setOnClickListener {
            val builder = context?.let { it1 -> AlertDialog.Builder(it1) }
            val alertTasarim =
                LayoutInflater.from(context).inflate(R.layout.siparis_tamamlandi_alert, null)
            builder?.setView(alertTasarim)
            val d = builder?.create()
            d?.show()
            viewModel.tumYemekleriSil()
            Handler().postDelayed({
                d?.dismiss()
            }, 1500)

        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetOnayFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val konum = LatLng(37.00, 31.00)
        mMap.addMarker(MarkerOptions().position(konum).title("Konum"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(konum))
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
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