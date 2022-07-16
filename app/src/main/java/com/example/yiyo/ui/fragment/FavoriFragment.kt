package com.example.yiyo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FavoriFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }
}