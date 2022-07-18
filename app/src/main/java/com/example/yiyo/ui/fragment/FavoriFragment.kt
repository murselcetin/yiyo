package com.example.yiyo.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.yiyo.R
import com.example.yiyo.databinding.FragmentAnasayfaBinding
import com.example.yiyo.databinding.FragmentFavoriBinding
import com.example.yiyo.ui.adapter.FavorilerAdapter
import com.example.yiyo.ui.adapter.YemeklerAdapter
import com.example.yiyo.ui.viewmodel.AnasayfaFragmentViewModel
import com.example.yiyo.ui.viewmodel.FavoriFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriFragment : Fragment() {
    private lateinit var binding: FragmentFavoriBinding
    private lateinit var viewModel: FavoriFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriBinding.inflate(layoutInflater, container, false)
        binding.favoriFragment = this

        viewModel.favorilerListesi.observe(viewLifecycleOwner) {
            val adapter = FavorilerAdapter(requireContext(), it, viewModel)
            binding.favoriAdapter = adapter
        }

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavBar, navHostFragment.navController)

        binding.bottomNavBar.background = null
        binding.bottomNavBar.menu.getItem(1).isEnabled = false

        binding.fab.setOnClickListener {
            val modalBottomSheet = SepetFragment()
            modalBottomSheet.show(requireActivity().supportFragmentManager, "ModalBottomSheet")
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FavoriFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    val builder = AlertDialog.Builder(context)
                    val alertTasarim = LayoutInflater.from(context).inflate(R.layout.sepetten_sil_alert, null)
                    val text = alertTasarim.findViewById(R.id.textViewAlertMesaj) as TextView
                    val evetButton = alertTasarim.findViewById(R.id.buttonEvet) as Button
                    val hayirButton = alertTasarim.findViewById(R.id.buttonHayir) as Button
                    text.text="Uygulamadan çıkmak istiyor musunuz?"
                    builder.setView(alertTasarim)
                    val d= builder.create()
                    evetButton.setOnClickListener {
                        activity?.finish()
                        d.dismiss()
                    }
                    hayirButton.setOnClickListener {
                        d.dismiss()
                    }
                    d.show()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }
}