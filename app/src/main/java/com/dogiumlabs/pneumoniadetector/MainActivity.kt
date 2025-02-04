package com.dogiumlabs.pneumoniadetector

import MyCameraViewfinder
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.dogiumlabs.pneumoniadetector.ui.dialog.StorageAlertDialog
import com.dogiumlabs.pneumoniadetector.ui.theme.PneumoniaDetectorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        launchApp()
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                showAppContent()
            } else {
                showRequestDialog()
            }
        }

    private fun launchApp() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                showAppContent()
            }
            else -> {
                showRequestDialog()
            }
        }
    }

    private fun showAppContent() {
        setContent {
            PneumoniaDetectorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyCameraViewfinder(

                    )
                }
            }
        }
    }

    private fun showRequestDialog() {
        setContent {
            PneumoniaDetectorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StorageAlertDialog(
                        onDismissRequest = { finish() },
                        onConfirmation = {
                            requestPermissionLauncher.launch(
                                android.Manifest.permission.CAMERA
                            )
                        },
                        dialogTitle = "Camera permission required",
                        dialogText = "This is a camera that requires access to camera." +
                                " Press Confirm to grant permission" +
                                " or Dismiss to exit the application.",
                        confirmText = "Confirm",
                        cancelText = "Exit App",
                        icon = Icons.Default.Warning,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}