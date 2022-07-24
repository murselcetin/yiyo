package com.example.yiyo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.yiyo.R
import com.example.yiyo.databinding.FragmentViewPagerBinding
import com.example.yiyo.ui.adapter.ViewPagerAdapter
import com.example.yiyo.ui.fragment.viewpager.BirinciFragment
import com.example.yiyo.ui.fragment.viewpager.GirisFragment
import com.example.yiyo.ui.fragment.viewpager.IkinciFragment
import com.example.yiyo.util.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding
    @Inject
    lateinit var prefs: MySharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            BirinciFragment(),
            IkinciFragment(),
        )

        binding.viewPager.registerOnPageChangeCallback(onTutorialPageChangeCallBack)

        val adapter =
            ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle, fragmentList)

        binding.viewPager.adapter = adapter

        return binding.root
    }

    private var onTutorialPageChangeCallBack = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.imageViewIleri.setOnClickListener {
                when (position) {
                    0 -> {
                        binding.viewPager.setCurrentItem(1, true)
                    }
                    1 -> {
                        val action = ViewPagerFragmentDirections.actionViewPagerFragment2ToGirisFragment2()
                        findNavController().navigate(action)
                        binding.imageViewIleri.visibility = View.GONE
                        prefs.viewPagerKontrol = true
                    }
                }
            }
        }
    }
}