package org.d3if3141.asessment3.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3141.asessment3.model.Wallpaper
import org.d3if3141.asessment3.network.WallpaperApi

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Wallpaper>())
        private set

    init {
        retrievedata()
    }

    private fun retrievedata() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.value = WallpaperApi.service.getWallpaper()
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}
