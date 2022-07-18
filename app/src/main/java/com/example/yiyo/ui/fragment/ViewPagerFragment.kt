package com.example.yiyo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yiyo.R
import com.example.yiyo.databinding.FragmentViewPagerBinding
import com.example.yiyo.ui.adapter.ViewPagerAdapter
import com.example.yiyo.ui.fragment.viewpager.BirinciFragment
import com.example.yiyo.ui.fragment.viewpager.GirisFragment
import com.example.yiyo.ui.fragment.viewpager.IkinciFragment


class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            BirinciFragment(),
            IkinciFragment(),
            GirisFragment(),
        )

        val adapter =
            ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle, fragmentList)

        binding.viewPager.adapter = adapter

        return binding.root
    }

}