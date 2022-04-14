package com.metehanbolat.alertcustomdialogcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.metehanbolat.alertcustomdialogcompose.types.emptyReturnUnit
import com.metehanbolat.alertcustomdialogcompose.ui.theme.AlertCustomDialogComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlertCustomDialogComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AlertDialogAndCustomDialog()
                }
            }
        }
    }
}

@Composable
fun AlertDialogAndCustomDialog() {

}

@Composable
fun DefaultAlertDialog(
    onDismiss: emptyReturnUnit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            securePolicy = SecureFlagPolicy.Inherit
        ),
        title = {
            Text(
                text = stringResource(id = R.string.default_dialog_title),
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Text(text = stringResource(id = R.string.default_dialog_text))
        },
        buttons = {
            OutlinedButton(
                onClick = onDismiss,
                shape = RoundedCornerShape(percent = 30),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(
                onClick = onDismiss,
                shape = RoundedCornerShape(percent = 30),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color(0xFF8BC34A),
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.okay))
            }
        }
    )
}
