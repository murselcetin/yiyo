package com.example.yiyo.ui.fragment.viewpager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.yiyo.R
import com.example.yiyo.databinding.FragmentIkinciBinding
import com.example.yiyo.util.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IkinciFragment : Fragment() {
    private lateinit var binding: FragmentIkinciBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIkinciBinding.inflate(layoutInflater, container, false)

        return binding.root
    }


}