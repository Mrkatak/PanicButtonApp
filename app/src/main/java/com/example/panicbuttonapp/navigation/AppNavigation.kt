package com.example.panicbuttonapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.panicbuttonapp.screens.HomeScreen
import com.example.panicbuttonapp.screens.LoginScreen
import com.example.panicbuttonapp.screens.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }
        // Tambahkan composable lain di sini jika diperlukan
    }
}