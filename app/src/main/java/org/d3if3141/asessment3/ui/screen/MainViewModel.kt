package org.d3if3141.asessment3.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3141.asessment3.network.WallpaperApi

class MainViewModel : ViewModel() {

    init {
        retrievedata()
    }

    private fun retrievedata() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = WallpaperApi.service.getWallpaper()
                Log.d("MainViewModel", "Success: $result")
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}
