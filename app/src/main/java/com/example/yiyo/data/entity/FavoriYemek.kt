package com.example.yiyo.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "favoriler")
data class FavoriYemek(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favori_id") @NotNull var favori_id: Int,
    @ColumnInfo(name = "yemek_resim_adi") @NotNull var yemek_resim_adi: String,
    @ColumnInfo(name = "yemek_adi") @NotNull var yemek_adi: String,
    @ColumnInfo(name = "yemek_fiyat") @NotNull var yemek_fiyat: Int
) : Serializable {}

