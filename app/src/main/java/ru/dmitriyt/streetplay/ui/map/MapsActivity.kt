package ru.dmitriyt.streetplay.ui.map

import android.os.Bundle
import android.view.LayoutInflater

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*
import ru.dmitriyt.streetplay.R
import ru.dmitriyt.streetplay.ui.global.BaseActivity

class MapsActivity : BaseActivity(), OnMapReadyCallback {
    override val layoutRes: Int = R.layout.activity_maps

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapFragment = map_fragment as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.setOnMarkerClickListener {
            bottomsheet.showWithSheetView(LayoutInflater.from(this).inflate(R.layout.bottom_sheet_place, bottomsheet, false))
            true
        }
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}
