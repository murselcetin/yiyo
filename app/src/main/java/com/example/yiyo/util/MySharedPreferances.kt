package com.example.yiyo.util

import android.content.SharedPreferences
import javax.inject.Inject

class MySharedPreferences @Inject constructor(var prefs:SharedPreferences) {

    var kullaniciAdi: String?
        get() = prefs.getString(Constants.KULLANICI_ADI, null)
        set(value) = prefs.edit().putString(Constants.KULLANICI_ADI, value).apply()

    var viewPagerKontrol: Boolean
        get() = prefs.getBoolean(Constants.VIEWPAGER_KONTROL,false)
        set(value) = prefs.edit().putBoolean(Constants.VIEWPAGER_KONTROL,value).apply()

    var girisKontrol: Boolean
        get() = prefs.getBoolean(Constants.VIEWPAGER_KONTROL,false)
        set(value) = prefs.edit().putBoolean(Constants.VIEWPAGER_KONTROL,value).apply()

    var tutar: Int
        get() = prefs.getInt(Constants.TUTAR,0)
        set(value) = prefs.edit().putInt(Constants.TUTAR,value).apply()
}