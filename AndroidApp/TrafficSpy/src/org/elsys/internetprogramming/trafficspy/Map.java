package org.elsys.internetprogramming.trafficspy;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class Map {
	private final String TAG = "MAP";
	
	private GoogleMap map;
	
	public Map(GoogleMap googleMap, Context context) {
		this.map = googleMap;
		
		this.map.setMyLocationEnabled(true);
	}
	
}
