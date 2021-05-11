package com.daps.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
/* import com.daps.app.service.CloudAPI
import com.daps.app.service.ServiceBuilder
import com.google.android.libraries.places.api.Places
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory  */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // https://developers.google.com/maps/documentation/places/android-sdk/start
        //Places.initialize(applicationContext, "AIzaSyDrzDcMBvxz0DSFIhm0vrzRDAaZi1VOOjs")
        // Create a new PlacesClient instance
        //val placesClient = Places.createClient(this)
    }

}