package com.faiz.patanistaticui.data

import android.content.Context
import com.faiz.patanistaticui.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val service = ApiConfig.getApiService()
        return Repository.getInstance(service)
    }
}