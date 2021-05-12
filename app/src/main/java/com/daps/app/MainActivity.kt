package com.daps.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


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