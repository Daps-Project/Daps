package com.daps.app

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //  val navController = navHostFragment.navController

        //  https://developers.google.com/maps/documentation/places/android-sdk/start
        //  Places.initialize(applicationContext, "AIzaSyDrzDcMBvxz0DSFIhm0vrzRDAaZi1VOOjs")
        //  Create a new PlacesClient instance
        //  val placesClient = Places.createClient(this)
        val fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",
            R.array.com_google_android_gms_fonts_certs
        )
        val config = FontRequestEmojiCompatConfig(this, fontRequest)
            .setEmojiSpanIndicatorEnabled(true)
            .setEmojiSpanIndicatorColor(Color.MAGENTA)
            .registerInitCallback(object : EmojiCompat.InitCallback() {})

        EmojiCompat.init(config)

    }

}