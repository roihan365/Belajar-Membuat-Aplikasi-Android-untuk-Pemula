package com.example.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myintentapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
//    private lateinit var btnMoveActivity: Button
//    private lateinit var btnMoveActivitywithData: Button
//    private lateinit var btnMoveActivitywithObject: Button
//    private lateinit var btnDialNumber: Button
//    private lateinit var btnMoveWithResult: Button
//    private lateinit var tvResult: TextView
    private lateinit var binding: ActivityMainBinding
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == MoveForResult.RESULT_CODE && result.data != null) {
            val selectedValue = result.data?.getIntExtra(MoveForResult.EXTRA_SELECTED_VALUE, 0)
            binding.tvResult.text = "Hasil: $selectedValue"
        }
    }

    private fun initComponents() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        binding.btnMoveActivity.setOnClickListener(this)
        binding.btnMoveActivityData.setOnClickListener(this)
        binding.btnMoveActivityObject.setOnClickListener(this)
        binding.btnDialNumber.setOnClickListener(this)
        binding.btnMoveActivityResult.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_moveActivity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_moveActivityData -> {
                val moveDataWithIntent = Intent(this@MainActivity, MoveWithData::class.java)
                moveDataWithIntent.putExtra(MoveWithData.EXTRA_NAME, "Dicoding Academy")
                moveDataWithIntent.putExtra(MoveWithData.EXTRA_AGE, "5")
                startActivity(moveDataWithIntent)
            }
            R.id.btn_moveActivityObject -> {
                val person = Person(
                    "DicodingAcademy",
                    5,
                    "dicoding@email.com",
                    "Banjarmasin"
                )
                val moveWIthObjectIntent = Intent(this@MainActivity, MoveWIthObject::class.java)
                moveWIthObjectIntent.putExtra(MoveWIthObject.EXTRA_PERSON, person)
                startActivity(moveWIthObjectIntent)
            }

            R.id.btn_dialNumber -> {
                val phoneNumber = "081312348122"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel: $phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_moveActivityResult -> {
                val moveForResultIntent = Intent(this@MainActivity, MoveForResult::class.java)
                resultLauncher.launch(moveForResultIntent)
            }
        }
    }
}