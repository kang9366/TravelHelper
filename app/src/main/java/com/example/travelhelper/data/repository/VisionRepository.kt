package com.example.travelhelper.data.repository

import android.net.Uri

interface VisionRepository {
    suspend fun getLandmarkInfo(imageUri: Uri): String
}