package com.faiz.patanistaticui.data.model

import retrofit2.http.Field
import java.io.File

data class DataSignin (
    var email: String,
    var password: String,
    var nama: String,
    var alamat: String,
    var kordinat: String,
    var no_hp: String,
    var image: File? = null
)