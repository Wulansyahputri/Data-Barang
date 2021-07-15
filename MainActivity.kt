package com.informatika.databarang

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.informatika.databarang.model.ResponseBarang
import com.informatika.databarang.model.ResponseUserItem
import com.informatika.databarang.network.koneksi
import kotlinx.android.synthetic.main.activity_update.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val i = Intent(this, InsertDataActivity::class.java)
            startActivity(i)
        }
        getData()
    }

    public fun getData(){
        koneksi.service.getUser().enqueue(object : Callback<ResponseBarang>{
            override fun onFailure(call: Call<List<ResponseUserItem?>?>, t: Throwable) {
               Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<List<ResponseUserItem?>?>,
                response: Response<List<ResponseUserItem?>?>
            ) {
               if(response.isSuccessful){
                   val dataBody = response.body()
                   val dataContent = dataBody!!.data

                   val rvAdabter = ListContent(dataContent, this@MainActivity)
                   rvAdabter.notiffyDataSetChanged()

                   rv_data_barang.apply {
                       adapter = rvAdabter
                       layoutManager = LinearLayoutManager(this@MainActivity)
                   }
               }
            }
        })
    }

    override fun onRestart() {
        super.onRestart()
        getData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}