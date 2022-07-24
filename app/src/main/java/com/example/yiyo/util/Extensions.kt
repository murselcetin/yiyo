package com.example.yiyo.util

import android.app.Activity
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

fun Double.convert(): String {
    val format = DecimalFormat("#,###.00")
    format.isDecimalSeparatorAlwaysShown = false
    return format.format(this).toString().plus(" ₺")
}

fun Navigation.gecisYap(v: View, id: NavDirections) {
    findNavController(v).navigate(id)
}

fun ImageView.resimYukle(resimAdi: String){
    val url = "http://kasimadalan.pe.hu/yemekler/resimler/${resimAdi}"
    Picasso.get().load(url).into(this)
}
fun Activity.displayMetrics(): DisplayMetrics {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics
}