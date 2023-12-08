package com.example.travelhelper.data.repository

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import com.example.travelhelper.domain.repository.VisionRepository
import com.google.android.gms.tasks.Tasks
import com.google.firebase.Firebase
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.functions
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmarkDetector
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VisionRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver
): VisionRepository {
    private lateinit var functions: FirebaseFunctions
    private fun convertToBitmap(uri: Uri): Bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri))

    private fun initFirebaseDetector(): FirebaseVisionCloudLandmarkDetector {
        functions = Firebase.functions
        val options = FirebaseVisionCloudDetectorOptions.Builder()
            .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
            .setMaxResults(1)
            .build()
        return FirebaseVision.getInstance().getVisionCloudLandmarkDetector(options)
    }

    override suspend fun getLandmarkInfo(imageUri: Uri): String {
        val bitmap = convertToBitmap(imageUri)
        val detector = initFirebaseDetector()
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        return withContext(Dispatchers.IO) {
            try {
                val landmarks = Tasks.await(detector.detectInImage(image))
                landmarks.joinToString(", ") { it.landmark }
            } catch (e: Exception) {
                e.printStackTrace()
                "Error: ${e.message}"
            }
        }
    }
}