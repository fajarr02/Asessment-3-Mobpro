package org.d3if3141.asessment3.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3141.asessment3.model.Wallpaper
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/fajarr02/Static-JSON/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WallpaperApiService {
    @GET("static-api.json")
    suspend fun getWallpaper():  List<Wallpaper>
}

object WallpaperApi {
    val service: WallpaperApiService by lazy {
        retrofit.create(WallpaperApiService::class.java)
    }

    fun getWallpaperUrl(imageId: String): String {
        return "$BASE_URL$imageId.jpg"
    }
}

enum class ApiStatus {LOADING, SUCCESS, FAILED}
