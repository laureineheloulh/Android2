package com.example.helou_ijreis_projet_crous

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


const val MAP_CROUS = "crous_map"

class CrousMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mView: View
    private lateinit var mapView: MapView
    private lateinit var crousShelf: ArrayList<Crous>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val argCrous = requireArguments().getSerializable(MAP_CROUS) as ArrayList<Crous>?
        crousShelf = argCrous ?: ArrayList()
        val supportMapFragment: SupportMapFragment = SupportMapFragment.newInstance()
        supportMapFragment.getMapAsync(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        crousShelf = requireArguments().getSerializable(MAP_CROUS) as ArrayList<Crous>
        mView = inflater.inflate(R.layout.fragment_crous_map, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = mView.findViewById(R.id.mapView)
        if(mapView != null){
            mapView.onCreate(null)
            mapView.onResume()
            mapView.getMapAsync(this)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(crous: ArrayList<Crous>) =
            CrousMapFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CROUS, crous)

                }
            }
    }

    override fun onMapReady(gmap: GoogleMap) {
        for(i in 0 until crousShelf.size){
        val location : LatLng = LatLng(crousShelf[i].latitude, crousShelf[i].longitude)
            val markerOptions = MarkerOptions()
            gmap.addMarker(markerOptions
                .position(location)
                .title(crousShelf[i].name)
                .snippet(crousShelf[i].type)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
        )

        gmap.moveCamera(CameraUpdateFactory.newLatLng(location))

         }
    }
}