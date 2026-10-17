package com.christa.vp_week4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.christa.vp_week4.soal1.CarApp
import com.christa.vp_week4.ui.theme.VP_Week4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VP_Week4Theme {
                CarApp()
            }
        }
    }
}