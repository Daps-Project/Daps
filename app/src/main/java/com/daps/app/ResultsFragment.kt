package com.daps.app

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.daps.app.view.PlacesAdapter
import com.google.android.libraries.places.api.Places

import kotlinx.android.synthetic.main.results_fragment_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultsFragment : Fragment(R.layout.results_fragment_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val call = RestApiService().getPlaceIDs()
                val context: Context? = context
                places_list.adapter = context?.let { PlacesAdapter(call, it) }
            }
        }
    }
}