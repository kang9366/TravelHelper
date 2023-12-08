package com.example.travelhelper.domain.repository

import android.net.Uri

interface VisionRepository {
    suspend fun getLandmarkInfo(imageUri: Uri): String
}