package com.informatika.databarang.network

import com.informatika.databarang.model.ResponseBarang
import com.informatika.databarang.model.ResponseUserItem
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST


interface ApiService {
    @GET("read.php")
    fun getUser(): Call<ResponseBarang>

    @FormUrlEncoded
    @POST("create.php")
    fun insertBarang(
        @field("Nama_barang") namaBarang: String?
        @field("Jumlah_barang") jmlBarang: String?
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("update.php")
    fun updateBarang(
        @field("id") id: String?
    ): Call<ResponseActionBarang>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteBarang(
        @field("id") id: String?
    ): Call<ResponseActionBarang>
}