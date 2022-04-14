package com.metehanbolat.alertcustomdialogcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.metehanbolat.alertcustomdialogcompose.ui.theme.AlertCustomDialogComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlertCustomDialogComposeTheme {

            }
        }
    }
}
