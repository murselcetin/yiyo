package com.example.yiyo.data.entity

import com.google.gson.annotations.SerializedName

data class ESCevap(
    @SerializedName("success") var success: Int,
    @SerializedName("message") var message: String
) {
}