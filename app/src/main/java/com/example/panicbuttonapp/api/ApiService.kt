package com.example.panicbuttonapp.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("registrasi.php")
    fun registerUser(
        @Field("nomor_rumah") nomorRumah: String,
        @Field("sandi") sandi: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(
        @Field("nomor_rumah") nomorRumah: String,
        @Field("sandi") sandi: String
    ): Call<ResponseBody>
}


