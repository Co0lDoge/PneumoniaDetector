package com.dogiumlabs.pneumoniadetector

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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dogiumlabs.pneumoniadetector.ui.camera.CameraScreen
import com.dogiumlabs.pneumoniadetector.ui.dialog.StorageAlertDialog
import com.dogiumlabs.pneumoniadetector.ui.theme.PneumoniaDetectorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        launchApp()
    }

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                showAppContent()
            } else {
                showUnavailabilityDialog()
            }
        }

    fun launchApp() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, android.Manifest.permission.CAMERA) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.
                showRequestDialog()
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    android.Manifest.permission.CAMERA)
            }
        }
    }

    private fun showAppContent() {
        setContent {
            PneumoniaDetectorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CameraScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun showRequestDialog() {
        setContent {
            StorageAlertDialog(
                onDismissRequest = { /*TODO*/ },
                onConfirmation = { /*TODO*/ },
                dialogTitle = "fwfw",
                dialogText = "fggwggwa",
                icon = Icons.Default.Warning
            )
        }
    }

    private fun showUnavailabilityDialog() {
        // TODO
    }
}