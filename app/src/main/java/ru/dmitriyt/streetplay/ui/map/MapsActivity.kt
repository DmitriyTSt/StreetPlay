package ru.dmitriyt.streetplay.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.bottom_sheet_place.*
import ru.dmitriyt.streetplay.App
import ru.dmitriyt.streetplay.R
import ru.dmitriyt.streetplay.domain.model.Place
import ru.dmitriyt.streetplay.presentation.map.IMapView
import ru.dmitriyt.streetplay.presentation.map.MapPresenter
import ru.dmitriyt.streetplay.ui.global.BaseActivity
import javax.inject.Inject

class MapsActivity : BaseActivity(), OnMapReadyCallback, IMapView, ClusterManager.OnClusterItemClickListener<Place> {

    override val layoutRes: Int = R.layout.activity_maps

    init {
        App.INSTANCE.appComponent.inject(this@MapsActivity)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: MapPresenter

    @ProvidePresenter
    fun provideMapPresenter() = MapPresenter()

    private lateinit var map: GoogleMap
    private var clusterManager: ClusterManager<Place>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapFragment = map_fragment as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        clusterManager = ClusterManager(this, map)
        clusterManager?.setOnClusterItemClickListener(this)
        map.setOnMarkerClickListener(clusterManager)
        presenter.getPlaces()
    }

    override fun onClusterItemClick(place: Place): Boolean {
        bottomsheet.showWithSheetView(LayoutInflater.from(this).inflate(R.layout.bottom_sheet_place, bottomsheet, false))
        place_title.text = place.name
        place_description.text = place.description
        return true
    }

    override fun showPlaces(places: List<Place>) {
        clusterManager?.clearItems()
        clusterManager?.addItems(places)
        clusterManager?.cluster()
    }

    private inner class PlaceRender: DefaultClusterRenderer<Place>(this, map, clusterManager) {
        override fun shouldRenderAsCluster(cluster: Cluster<Place>?): Boolean {
            return cluster?.size?:0 >= 2
        }
    }
}
