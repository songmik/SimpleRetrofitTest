package com.example.simpleretrofittest

import com.google.gson.annotations.SerializedName


// DTO 객체 -> 응답받은 데이터를 저장해놓는 객체로 다른 로직을 갖지 않는 순수한 데이터 객체
data class Data(
    @SerializedName("data1")
    val dataOne : String,
    @SerializedName("data2")
    val dataTwo : List<Two>
)

data class Two (
    @SerializedName("name")
    val name : String,
    @SerializedName("phone_number")
    val phoneNumber : String
    )

data class Result (
    @SerializedName ("result")
    val result : String
    )

data class ResponseKyc(
    @SerializedName("data1")
    val dataOne : String,
    @SerializedName("name")
    val name : String


)
