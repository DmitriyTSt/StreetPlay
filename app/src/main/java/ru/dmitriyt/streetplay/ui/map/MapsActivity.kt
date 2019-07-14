package ru.dmitriyt.streetplay.ui.map

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
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
import ru.dmitriyt.streetplay.ui.chat.ChatActivity
import ru.dmitriyt.streetplay.ui.global.BaseActivity
import ru.dmitriyt.streetplay.ui.place.PlaceCreateActivity
import ru.dmitriyt.streetplay.ui.settings.SettingsActivity
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
    fun providePresenter() = presenter

    private lateinit var map: GoogleMap
    private var clusterManager: ClusterManager<Place>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mapFragment = map_fragment as SupportMapFragment
        mapFragment.getMapAsync(this)
        btn_create_place.setOnClickListener {
            startActivity(Intent(this, PlaceCreateActivity::class.java))
        }
        btn_settings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
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
        bottomsheet.sheetView.image_list.adapter = PlaceImageAdapter(if (place.images != null) listOf(place.images) else ArrayList())
        bottomsheet.sheetView.place_last_date.text = "Последняя активность: %s".format(if (place.lastTime != null) DataUtil.getFormatTime(place.lastTime) else "Неизвестно")
        bottomsheet.sheetView.btn_chat.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java).putExtra("placeId", place.id))
        }
        return true
    }

    override fun showPlaces(places: List<Place>) {
        clusterManager?.clearItems()
        clusterManager?.addItems(places)
        clusterManager?.cluster()
    }

    override fun showError(message: String?) {
        Snackbar.make(bottomsheet, message?:"Неизвестная ошибка, попробуйте позже", Snackbar.LENGTH_SHORT).show()
    }

    private inner class PlaceRender: DefaultClusterRenderer<Place>(this, map, clusterManager) {
        override fun shouldRenderAsCluster(cluster: Cluster<Place>?): Boolean {
            return cluster?.size?:0 >= 2
        }
    }
}
