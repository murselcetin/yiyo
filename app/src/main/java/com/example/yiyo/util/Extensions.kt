package com.example.yiyo.util

import android.view.View
import android.widget.ImageView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.squareup.picasso.Picasso

fun Navigation.gecisYap(v: View, id: Int) {
    findNavController(v).navigate(id)
}

fun Navigation.gecisYap(v: View, id: NavDirections) {
    findNavController(v).navigate(id)
}

fun ImageView.resimYukle(resimAdi: String){
    val url = "http://kasimadalan.pe.hu/yemekler/resimler/${resimAdi}"
    Picasso.get().load(url).into(this)
}