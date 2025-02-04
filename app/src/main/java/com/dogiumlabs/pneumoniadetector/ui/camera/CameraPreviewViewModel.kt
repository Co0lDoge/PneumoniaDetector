package com.dogiumlabs.pneumoniadetector.ui.camera

import android.util.Size
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CameraPreviewViewModel: ViewModel() {
    private val _surfaceRequests = MutableStateFlow<SurfaceRequest?>(null)

    val surfaceRequests: StateFlow<SurfaceRequest?>
        get() = _surfaceRequests.asStateFlow()

    private fun produceSurfaceRequests(previewUseCase: Preview) {
        // Always publish new SurfaceRequests from Preview
        previewUseCase.setSurfaceProvider { newSurfaceRequest ->
            _surfaceRequests.value = newSurfaceRequest
        }
    }

    fun focusOnPoint(surfaceBounds: Size, x: Float, y: Float) {
        // Create point for CameraX's CameraControl.startFocusAndMetering() and submit...
    }

    // ...
}