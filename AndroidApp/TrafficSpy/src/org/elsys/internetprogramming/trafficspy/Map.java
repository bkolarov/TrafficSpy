package org.elsys.internetprogramming.trafficspy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends BroadcastReceiver implements OnCameraChangeListener, OnMapClickListener {
	public static final String BROADCAST_RECEIVER_BANE = "LocationUpdates";
	private final String TAG = "MAP";
	
	private GoogleMap map;
	
	private final float MAX_MAP_ZOOM = 18.0f;
	
	public Map(GoogleMap googleMap, Context context) {
		this.map = googleMap;
		
		this.map.setMyLocationEnabled(true);
		this.map.setOnCameraChangeListener(this);
		this.map.setTrafficEnabled(true);
		this.map.setOnMapClickListener(this);
	}

	@Override
	public void onCameraChange(CameraPosition position) {
		
	}

	@Override
	public void onReceive(final Context context, final Intent intent) {
		final double latitude = intent.getExtras().getDouble(LocationService.LATITUDE);
		final double longitude = intent.getExtras().getDouble(LocationService.LONGITUDE);
		
		final LatLng latLng = new LatLng(latitude, longitude);
		this.moveCamera(latLng);
	}
	
	private void moveCamera(final LatLng latLng) {
		final CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, this.MAX_MAP_ZOOM);
		this.map.animateCamera(cameraUpdate);
	}

	@Override
	public void onMapClick(LatLng point) {
		this.map.addMarker(new MarkerOptions().position(point));
		
	}
	
}
