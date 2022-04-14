package com.metehanbolat.alertcustomdialogcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
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

@Composable
fun CustomAlertDialog(
    onDismiss: emptyReturnUnit,
    onNegativeClick: emptyReturnUnit,
    onPositiveClick: emptyReturnUnit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        val color = Color(0xFF4DB64C)

        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(color)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = stringResource(id = R.string.alert_dialog_icon_description),
                        modifier = Modifier
                            .graphicsLayer(
                                scaleX = 1.2f,
                                scaleY = 1.2f
                            )
                            .align(Alignment.Center)
                    )
                }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(text = stringResource(id = R.string.custom_dialog_text))
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(id = R.string.not_now),
                        color = color,
                        modifier = Modifier
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = rememberRipple(color = Color.DarkGray),
                                onClick = onNegativeClick
                            )
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(id = R.string._continue),
                        color = color,
                        modifier = Modifier
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = rememberRipple(color = Color.DarkGray),
                                onClick = onPositiveClick
                            )
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}
