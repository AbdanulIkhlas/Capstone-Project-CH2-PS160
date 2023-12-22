package com.faiz.patanistaticui.ui.screen.signin

import androidx.lifecycle.ViewModel
import com.faiz.patanistaticui.data.Repository
import java.io.File

class SigninViewModel (
    private val repository: Repository
) : ViewModel() {
    suspend fun registerBuyer(
        email: String,
        password: String,
        name: String,
        alamat: String,
        koordinat: String,
        no_hp: String,
        image: File? = null
    ) = repository.registerBuyer(email, password, name,  alamat, koordinat, no_hp, image)
}