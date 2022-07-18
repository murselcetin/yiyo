package com.example.yiyo.ui.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.yiyo.R
import com.example.yiyo.data.entity.Yemekler
import com.example.yiyo.databinding.FragmentAnasayfaBinding
import com.example.yiyo.ui.adapter.YemeklerAdapter
import com.example.yiyo.ui.viewmodel.AnasayfaFragmentViewModel
import com.example.yiyo.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false)
        binding.anasayfaFragment = this

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavBar, navHostFragment.navController)

        binding.bottomNavBar.background = null
        binding.bottomNavBar.menu.getItem(1).isEnabled = false

        binding.imageViewKullanici.setOnClickListener {
            val action = AnasayfaFragmentDirections.actionAnasayfaFragmentToKullaniciFragment()
            findNavController().navigate(action)
        }

        binding.fab.setOnClickListener {
            val modalBottomSheet = SepetFragment()
            modalBottomSheet.show(requireActivity().supportFragmentManager, "ModalBottomSheet")
        }
        viewModel.yemeklerListesi.observe(viewLifecycleOwner) {
            val adapter = YemeklerAdapter(requireContext(), it, viewModel)
            binding.yemeklerAdapter = adapter
        }
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaFragmentViewModel by viewModels()
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