
import androidx.camera.compose.CameraXViewfinder
import androidx.camera.core.SurfaceRequest
import androidx.camera.viewfinder.compose.MutableCoordinateTransformer
import androidx.camera.viewfinder.surface.ImplementationMode
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dogiumlabs.pneumoniadetector.ui.camera.CameraPreviewViewModel

@Composable
fun MyCameraViewfinder(
    modifier: Modifier = Modifier,
    viewModel: CameraPreviewViewModel = viewModel(),
) {
    val currentSurfaceRequest: SurfaceRequest? by viewModel.surfaceRequests.collectAsState()

    currentSurfaceRequest?.let { surfaceRequest ->

        // CoordinateTransformer for transforming from Offsets to Surface coordinates
        val coordinateTransformer = remember { MutableCoordinateTransformer() }

        CameraXViewfinder(
            surfaceRequest = surfaceRequest,
            implementationMode = ImplementationMode.EXTERNAL, // Can also use EMBEDDED
            modifier =
            modifier.pointerInput(Unit) {
                detectTapGestures {
                    with(coordinateTransformer) {
                        val surfaceCoords = it.transform()
                        viewModel.focusOnPoint(
                            surfaceRequest.resolution,
                            surfaceCoords.x,
                            surfaceCoords.y
                        )
                    }
                }
            },
            coordinateTransformer = coordinateTransformer
        )
    }
}