package com.informatika.databarang.model

import com.google.gson.annotations.SerializedName

data class ResponseActionBarang(
    @field:SerializedName("pesan")
    val pesan: Any? = null,

    @field:SerializedName("data")
    val pesan: List<Boolean?>? = null,

    @field:SerializedName("status")
    val pesan: String? = null,
)