package com.example.yiyo.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.yiyo.R
import com.example.yiyo.util.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AcilisFragment : Fragment() {

    @Inject
    lateinit var prefs : MySharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        Handler().postDelayed({
            if (onBoardingFinished()){
                val action = AcilisFragmentDirections.actionAcilisFragment2ToGirisFragment()
                findNavController().navigate(action)
            }else{
                val action = AcilisFragmentDirections.actionAcilisFragment2ToViewPagerFragment2()
                findNavController().navigate(action)
            }
        }, 3000)

        return inflater.inflate(R.layout.fragment_acilis, container, false)
    }

    fun onBoardingFinished(): Boolean{
        return prefs.viewPagerKontrol
    }
}