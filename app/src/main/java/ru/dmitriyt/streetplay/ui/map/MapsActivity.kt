package ru.dmitriyt.streetplay.ui.map

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

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
import kotlinx.android.synthetic.main.bottom_sheet_place.view.*
import ru.dmitriyt.streetplay.App
import ru.dmitriyt.streetplay.R
import ru.dmitriyt.streetplay.data.system.DataUtil
import ru.dmitriyt.streetplay.domain.model.Place
import ru.dmitriyt.streetplay.presentation.map.IMapView
import ru.dmitriyt.streetplay.presentation.map.MapPresenter
import ru.dmitriyt.streetplay.ui.global.BaseActivity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MapsActivity :
    BaseActivity(),
    OnMapReadyCallback,
    IMapView,
    ClusterManager.OnClusterItemClickListener<Place>,
    GoogleMap.OnCameraIdleListener {

    override val layoutRes: Int = R.layout.activity_maps

    init {
        App.INSTANCE.appComponent.inject(this@MapsActivity)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: MapPresenter

    @ProvidePresenter
    fun providePresenter() = MapPresenter()

    private lateinit var map: GoogleMap
    private var clusterManager: ClusterManager<Place>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapFragment = map_fragment as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        clusterManager = ClusterManager(this, map)
        clusterManager?.setOnClusterItemClickListener(this)
        clusterManager?.renderer = PlaceRender()
        map.uiSettings.isMapToolbarEnabled = false
        map.isMyLocationEnabled = true
        map.setOnMarkerClickListener(clusterManager)
        map.setOnCameraIdleListener(this)
    }

    override fun onCameraIdle() {
        presenter.getPlaces()
        clusterManager?.onCameraIdle()
    }

    override fun onClusterItemClick(place: Place): Boolean {
        bottomsheet.showWithSheetView(LayoutInflater.from(this).inflate(R.layout.bottom_sheet_place, bottomsheet, false))
        bottomsheet.sheetView.place_title.text = place.name
        bottomsheet.sheetView.place_description.text = place.description
        val lm = FlexboxLayoutManager(this)
        lm.flexWrap = FlexWrap.WRAP
        bottomsheet.sheetView.image_list.layoutManager = lm
        bottomsheet.sheetView.image_list.adapter = PlaceImageAdapter(place.images?:ArrayList())
        bottomsheet.sheetView.place_last_date.text = "Последняя активность: %s".format(DataUtil.getFormatTime(place.getLastOnline()))
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
