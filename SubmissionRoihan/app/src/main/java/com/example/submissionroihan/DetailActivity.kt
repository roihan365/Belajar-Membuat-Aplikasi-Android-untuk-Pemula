package com.example.submissionroihan

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.submissionroihan.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_WISATA = "extra_wisata"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wisata = intent.getParcelableExtra<Wisata>(EXTRA_WISATA)

        if ( wisata != null) {
            binding.tvNamaWisata.text = wisata.namaWisata
            binding.tvLokasi.text = wisata.lokasi
            binding.tvJamBuka.text = wisata.jam_buka
            binding.tvBiaya.text = wisata.biaya
            binding.tvDeskripsi.text = wisata.deskripsi
            binding.imgPhotoDetail.setImageResource(wisata.photo)
        }

        val btnshare: Button = binding.actionShare
        btnshare.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v) {
            binding.actionShare -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Ayo liburan ke ${binding.tvNamaWisata.text} lokasinya dekat kok ada di ${binding.tvLokasi.text}, jam bukanya mulai dari ${binding.tvJamBuka.text}")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }
}