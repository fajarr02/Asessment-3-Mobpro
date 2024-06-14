package org.d3if3141.asessment3.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.d3if3141.asessment3.model.Wallpaper
import org.d3if3141.asessment3.network.ApiStatus
import org.d3if3141.asessment3.network.WallpaperApi

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Wallpaper>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    init {
        retrievedata()
    }

fun retrievedata() {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = WallpaperApi.service.getWallpaper()
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }
}
