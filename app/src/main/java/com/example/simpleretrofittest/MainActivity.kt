package com.example.simpleretrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.simpleretrofittest.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(20000L, TimeUnit.SECONDS)
            .build()


        // Retrofit 초기화
       val retrofit = Retrofit.Builder()
            .baseUrl("https://aml-api-dev.rootone.com/")
            .client(client)
                // JSON을 변환해줄 Gson 변환기 등록
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // create 메서드에 Service 클래스를 넣어 HTTP에 접근할 Retrofit 객체를 만든다
        val service = retrofit.create(Service::class.java)


        val data = Two(
            name = binding.nameEditText.text.toString(),
            phoneNumber = binding.numberEditText.text.toString()
        )


        // Enqueue로 비동기 통신 실행 -> API 통신
        service.getData(data).enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    Log.d("Response Test", response.body().toString())
                    binding.textView.text = response.body().toString()
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}