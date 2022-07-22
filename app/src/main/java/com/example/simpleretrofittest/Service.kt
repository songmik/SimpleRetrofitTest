package com.example.simpleretrofittest

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Service {

    @POST("kyc/insert")
    fun getData(@Body body : Two) : Call<Result>

    // Call<> 안에 응답받을 Body 타입의 data class를 적으면 된다
}