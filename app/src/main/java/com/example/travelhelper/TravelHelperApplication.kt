package com.example.travelhelper

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TravelHelperApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}