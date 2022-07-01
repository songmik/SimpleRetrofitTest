package com.example.simpleretrofittest

import retrofit2.Call
import retrofit2.http.POST

interface Service {

    @POST("kyc/insert")
    fun getData() : Call<Data>
}