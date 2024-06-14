package org.d3if3141.asessment3.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3141.asessment3.model.OpStatus
import org.d3if3141.asessment3.model.Wallpaper
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://unspoken.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WallpaperApiService {
    @GET("api_yaumil.php")
    suspend fun getWallpaper(
        @Header("Authorization") userId: String
    ): List<Wallpaper>


    @Multipart
    @POST("api_yaumil.php")
    suspend fun postWallpaper(
        @Header("Authorization") userId: String,
        @Part("wallpaper") wallpaper: RequestBody,
        @Part("impression") impression: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("api_yaumil.php")
    suspend fun deleteWallpaper(
        @Header("Authorization") userId: String,
        @Query("id") id: String
    ): OpStatus
}

object WallpaperApi {
    val service: WallpaperApiService by lazy {
        retrofit.create(WallpaperApiService::class.java)
    }

    fun getWallpaperUrl(imageId: String): String {
        return "${BASE_URL}image.php?id=$imageId"
    }
}

enum class ApiStatus {LOADING, SUCCESS, FAILED}
