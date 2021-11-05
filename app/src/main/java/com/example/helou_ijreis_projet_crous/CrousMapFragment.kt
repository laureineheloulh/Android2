package com.example.helou_ijreis_projet_crous

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class CrousMapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var crousShelf: ArrayList<Crous>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* val supportMapFragment: SupportMapFragment = SupportMapFragment.newInstance()
        supportMapFragment.getMapAsync(this)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.map_layout, supportMapFragment)
        fragmentTransaction.commit()*/

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crous_map, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(): CrousMapFragment =
            CrousMapFragment()

    }

    override fun onMapReady(gmap: GoogleMap) {
        for(i in 0 until crousShelf.size){
        val location : LatLng = LatLng(crousShelf[i].latitude, crousShelf[i].longitude)
        gmap.addMarker(
            MarkerOptions()
                .position(location)
                .title(crousShelf[i].name)
                .snippet(crousShelf[i].type)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
        )

        gmap.moveCamera(CameraUpdateFactory.newLatLng(location))

         }
    }
}