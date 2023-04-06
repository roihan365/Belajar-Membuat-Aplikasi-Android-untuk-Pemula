package com.example.submissionroihan

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wisata(
    val namaWisata: String,
    val lokasi: String,
    val photo: Int,
    val jam_buka: String,
    val biaya: String,
    val deskripsi: String

) : Parcelable
