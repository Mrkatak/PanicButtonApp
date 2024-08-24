package com.example.panicbuttonapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.panicbuttonapp.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import okio.IOException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel() {
    fun registerUser(nomorRumah: String, sandi: String, onResult: (String) -> Unit) {
        val call = RetrofitClient.instance.registerUser(nomorRumah, sandi)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    onResult("Registrasi berhasil")
                } else {
                    onResult("Registrasi gagal: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onResult("Registrasi gagal: ${t.message}")
            }
        })
    }

    fun loginUser(
        nomorRumah: String,
        sandi: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val url = "http://192.168.48.159/button/login_check.php"

        val formBody = FormBody.Builder()
            .add("nomor_rumah", nomorRumah)
            .add("sandi", sandi)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        val client = OkHttpClient()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val jsonResponse = JSONObject(response.body?.string() ?: "")
                    val status = jsonResponse.getString("status")

                    if (status == "success") {
                        launch(Dispatchers.Main) { onSuccess() }
                    } else {
                        val message = jsonResponse.getString("message")
                        launch(Dispatchers.Main) { onError(message) }
                    }
                } else {
                    launch(Dispatchers.Main) { onError("Server Error") }
                }
            } catch (e: IOException) {
                launch(Dispatchers.Main) { onError("Network Error") }
            }
        }
    }
}