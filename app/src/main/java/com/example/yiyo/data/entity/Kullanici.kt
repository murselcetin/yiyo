package com.example.yiyo.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "kullanicilar")
data class Kullanici(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "kullanici_id") @NotNull var kullanici_id: Int = 0,
    @ColumnInfo(name = "kullanici_adi") @NotNull var kullanici_adi: String,
    @ColumnInfo(name = "sifre") @NotNull var sifre: String
):Serializable{
}
