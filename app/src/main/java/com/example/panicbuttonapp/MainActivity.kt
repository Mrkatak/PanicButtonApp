package com.example.panicbuttonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.panicbuttonapp.navigation.AppNavigation
import com.example.panicbuttonapp.ui.theme.PanicButtonAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PanicButtonAppTheme {
                AppNavigation()
            }
        }
    }
}
