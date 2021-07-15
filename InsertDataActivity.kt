package com.informatika.databarang

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_insert_data.*
import kotlinx.android.synthetic.main.activity_insert_data.toolbar
import kotlinx.android.synthetic.main.activity_update.*

class InsertDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        toolbar.title = "INSERT DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        btn_submit.setOnClickListener{
            val etNamaBarang = et_nama_barang.text
            val etJmlBarang = et_jumlah_barang.text
            if(etJmlBarang.isEmpty()){
                Toast.makeText(this@InsertDataActivity, "Jumlah Barang Tidak Boleh Kosong", Toast.LENGTH_LONG.show()
            }else if(etNamaBarang.isEmpty()){
                Toast.makeText(this@InsertDataActivity, "Nama Barang Tidak Boleh Kosong", Toast.LENGTH_LONG.show()
            }else{
                actionData(etNamaBarang).toString(), etJmlBarang.toString())
            }
        }
        btn_clean.setOnClickListener{
            formClear()
        }
        getData
    }
}