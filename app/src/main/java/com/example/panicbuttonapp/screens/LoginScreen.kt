package com.example.panicbuttonapp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.panicbuttonapp.viewmodel.ViewModel

@Composable
fun LoginScreen(navController: NavController, loginViewModel: ViewModel = viewModel()) {
    var nomorRumah by remember { mutableStateOf("") }
    var sandi by remember { mutableStateOf("") }
    val loading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Login",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        OutlinedTextField(
            value = nomorRumah,
            onValueChange = { nomorRumah = it },
            label = { Text("Nomor Rumah") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = sandi,
            onValueChange = { sandi = it },
            label = { Text("Sandi") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                loginViewModel.loginUser(
                    nomorRumah = nomorRumah,
                    sandi = sandi,
                    onSuccess = {
                        navController.navigate("home") // Pindah ke HomeScreen jika login berhasil
                        Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
                    },
                    onError = { message ->
                        errorMessage = message // Tampilkan pesan kesalahan jika login gagal
                        Toast.makeText(context, "Login Gagal: Nomer rumah atau sandi salah", Toast.LENGTH_SHORT).show()
                    }
                )
            },
            enabled = !loading
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.dp
                )
            } else {
                Text("Masuk")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Daftar",
            style = TextStyle(color = MaterialTheme.colorScheme.primary, fontSize = 16.sp),
            modifier = Modifier.clickable {
                navController.navigate("register")
            }
        )
    }
}