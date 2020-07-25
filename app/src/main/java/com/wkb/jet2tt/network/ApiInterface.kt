package com.wkb.jet2tt.network


import com.wkb.jet2tt.model.ArticalModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {


    /* Interface method Call Artical*/
     @GET("blogs?")
     fun getArtical(@Query("page") page: Int, @Query("limit") limit: Int): Call<List<ArticalModel>>


    companion object Factory {
        val BASE_URL = "https://5e99a9b1bc561b0016af3540.mockapi.io/jet2/api/v1/"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }

}