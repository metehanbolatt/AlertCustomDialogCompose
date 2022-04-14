package com.metehanbolat.alertcustomdialogcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
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
                    AlertDialogAndDialog()
                }
            }
        }
    }
}

@Composable
fun AlertDialogAndDialog() {
    var showAlertDialog by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                showAlertDialog = !showAlertDialog
            }
        ) {
            Text(
                text = stringResource(id = R.string.open_alert_dialog)
            )
        }
        if (showAlertDialog) {
            AlertDialogExample {
                showAlertDialog = !showAlertDialog
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                showDialog = !showDialog
            }
        ) {
            Text(
                text = stringResource(id = R.string.open_dialog)
            )
        }
        if (showDialog) {
            DialogExample(
                onDismiss = {
                    showDialog = !showDialog
                    Toast.makeText(context, R.string.dialog_dismissed, Toast.LENGTH_SHORT).show()
                },
                onNegativeClick = {
                    showDialog = !showDialog
                    Toast.makeText(context, R.string.negative_button_clicked, Toast.LENGTH_SHORT).show()

                },
                onPositiveClick = {
                    showDialog = !showDialog
                    Toast.makeText(context, R.string.positive_button_clicked, Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Composable
fun AlertDialogExample(
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
                shape = RoundedCornerShape(percent = 30),
                onClick = onDismiss,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(
                shape = RoundedCornerShape(percent = 30),
                onClick = onDismiss,
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color(0xff8BC34A),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.okay))
            }
        }
    )
}

@Composable
fun DialogExample(
    onDismiss: emptyReturnUnit,
    onNegativeClick: emptyReturnUnit,
    onPositiveClick: emptyReturnUnit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        val color = Color(0xff4DB6AC)

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
                        tint = Color.White,
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .graphicsLayer(scaleX = 1.2f, scaleY = 1.2f)
                            .align(Alignment.Center)
                    )
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(stringResource(id = R.string.custom_dialog_text))
                    }
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
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
}