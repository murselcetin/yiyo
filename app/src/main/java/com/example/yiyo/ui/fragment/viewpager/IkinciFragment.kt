package com.example.yiyo.ui.fragment.viewpager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.yiyo.R
import com.example.yiyo.databinding.FragmentIkinciBinding


class IkinciFragment : Fragment() {
    private lateinit var binding: FragmentIkinciBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIkinciBinding.inflate(layoutInflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.imageViewIleri.setOnClickListener{
            viewPager?.currentItem = 2
            onBoardingFinished()
        }
        return binding.root
    }

    fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished",true)
        editor.apply()
    }

}