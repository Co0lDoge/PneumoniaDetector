package com.dogiumlabs.pneumoniadetector.ui.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.dogiumlabs.pneumoniadetector.ui.theme.PneumoniaDetectorTheme

@Composable
fun StorageAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        },
        modifier = modifier,
    )
}

@Composable
@Preview(showBackground = true)
private fun AlertDialogPreview() {
    PneumoniaDetectorTheme {
        Surface {
            StorageAlertDialog(
                onDismissRequest = { /*TODO*/ },
                onConfirmation = { /*TODO*/ },
                dialogTitle = "Dialog Title",
                dialogText = "Dialog read this: press confirm to confirm" +
                        ", press dismiss to dismiss.",
                icon = Icons.Default.Warning
            )
        }
    }
}