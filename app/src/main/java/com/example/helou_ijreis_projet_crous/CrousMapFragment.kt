package com.example.helou_ijreis_projet_crous

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


const val MAP_CROUS = "crous_map"

class CrousMapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

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
                    putSerializable(MAP_CROUS, crous)

                }
            }
    }

    override fun onMapReady(gmap: GoogleMap) {
        gmap.setOnInfoWindowClickListener(this)
        for(i in 0 until crousShelf.size){
            val location : LatLng = LatLng(crousShelf[i].geolocalisation[0], crousShelf[i].geolocalisation[1])
                val markerOptions = MarkerOptions()
                gmap.addMarker(markerOptions
                    .position(location)
                    .title(crousShelf[i].title)
                    .snippet(crousShelf[i].type)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
            )
        }
        //position de notre belle école
        gmap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(47.845765, 5.94)))
    }

    override fun onInfoWindowClick(marker: Marker) {
        val crous: Crous = crousShelf.filter {
            it.title == marker.title
        }.first()
        Toast.makeText(context, "Item ${crous.title} clicked", Toast.LENGTH_SHORT).show()

        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(ARG_CROUS, crous)
        intent.putExtra("fav", crous.favorite)
        this.startActivityForResult(intent, 1)
    }
}