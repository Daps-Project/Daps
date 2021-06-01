package com.daps.app.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daps.app.R
import com.google.android.gms.common.api.ApiException
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPhotoRequest
import com.google.android.libraries.places.api.net.FetchPhotoResponse
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import kotlinx.android.synthetic.main.results_layout.view.*

class PlacesAdapter(placeIDs: List<String>, context: Context) : RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>() {
    private val places : List<String> = placeIDs
    private val context = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        return PlacesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.bind(places[position])
    }

    override fun getItemCount(): Int = places.size

    inner class PlacesViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) :
                this(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.results_layout, parent, false)
                )
        fun bind(placeID: String){
            val fields = listOf(Place.Field.PHOTO_METADATAS)
            val placeRequest = FetchPlaceRequest.newInstance(placeID, fields)
            val placesClient = Places.createClient(context)
            placesClient?.fetchPlace(placeRequest)
                ?.addOnSuccessListener { response: FetchPlaceResponse ->
                    val place = response.place

                    // Get the photo metadata.
                    val metada = place.photoMetadatas
                    if (metada == null || metada.isEmpty()) {
                        return@addOnSuccessListener
                    }
                    val photoMetadata = metada.first()

                    // Get the attribution text.
                    val attributions = photoMetadata?.attributions

                    // Create a FetchPhotoRequest.
                    val photoRequest = FetchPhotoRequest.builder(photoMetadata)
                        .setMaxWidth(500) // Optional.
                        .setMaxHeight(300) // Optional.
                        .build()
                    placesClient.fetchPhoto(photoRequest)
                        .addOnSuccessListener { fetchPhotoResponse: FetchPhotoResponse ->
                            val bitmap = fetchPhotoResponse.bitmap
                            itemView.place_image.setImageBitmap(bitmap)
                        }.addOnFailureListener { exception: Exception ->
                            if (exception is ApiException) {
                                val statusCode = exception.statusCode
                            }
                        }
                }
        }
    }
}