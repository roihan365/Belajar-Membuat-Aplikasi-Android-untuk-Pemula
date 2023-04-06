package com.example.submissionroihan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submissionroihan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvWisata: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Wisata>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvWisata = binding.rvWisata
        rvWisata.setHasFixedSize(false)

        list.addAll(getListWisata())
        showRecyclerList()

    }

    private fun getListWisata(): ArrayList<Wisata> {
        val dataName = resources.getStringArray(R.array.tempat_wisata)
        val dataLokasi = resources.getStringArray(R.array.lokasi_wisata)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataBiaya = resources.getStringArray(R.array.biaya_masuk)
        val dataJam = resources.getStringArray(R.array.jam_buka)
        val dataDeskripsi = resources.getStringArray(R.array.deskripsi)
        val listWisata = ArrayList<Wisata>()
        for (i in dataName.indices) {
            val wisata = Wisata(
                dataName[i],
                dataLokasi[i],
                dataPhoto.getResourceId(i, -1),
                dataJam[i],
                dataBiaya[i],
                dataDeskripsi[i]
                )
            listWisata.add(wisata)
        }
        return listWisata
    }

    private fun showRecyclerList() {
        rvWisata.layoutManager = LinearLayoutManager(this)
        val listWisataAdapter = ListWisataAdapter(list)
        rvWisata.adapter = listWisataAdapter

        listWisataAdapter.setOnItemClickCallback(object: ListWisataAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Wisata) {
                showSelectedWisata(data)
            }
        })
    }

    private fun showSelectedWisata(wisata: Wisata) {
//        Toast.makeText(this, "Kamu memilih " + wisata.namaWisata, Toast.LENGTH_SHORT).show()
        val moveDetail = Intent(this@MainActivity, DetailActivity::class.java)
        moveDetail.putExtra(DetailActivity.EXTRA_WISATA, wisata)

        startActivity(moveDetail)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val moveAbout = Intent(this@MainActivity, AboutActivity::class.java)
        startActivity(moveAbout)
        return super.onOptionsItemSelected(item)
    }
}