package org.elsys.internetprogramming.trafficspy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.ibm.icu.text.Transliterator;

public class Map implements OnCameraChangeListener, OnMapClickListener,
		MyLocationListener {
	public static final String BROADCAST_RECEIVER_BANE = "LocationUpdates";
	private final String TAG = "MAP";

	private GoogleMap map;
	private LocationListener locationListener;
	private Context context;

	private Gson gson;

	private final float MAX_MAP_ZOOM = 18.0f;

	public Map(GoogleMap googleMap, Context context) {
		this.context = context;
		this.locationListener = new LocationListener(this.context, this);
		this.map = googleMap;

		this.map.setMyLocationEnabled(true);
		this.map.setOnCameraChangeListener(this);
		this.map.setTrafficEnabled(true);
		this.map.setOnMapClickListener(this);
		this.gson = new Gson();
	}

	@Override
	public void onCameraChange(CameraPosition position) {

	}

	private void moveCamera(LatLng latLng) {
		final CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
				latLng, this.MAX_MAP_ZOOM);

		if (this.map != null) {
			this.map.animateCamera(cameraUpdate);
		}
	}

	@Override
	public void onMapClick(LatLng point) {
		this.map.addMarker(new MarkerOptions().position(point));
		this.sendMarker(getPointAddress(point), point);
	}

	private String getPointAddress(LatLng point) {
		final Geocoder geocoder = new Geocoder(this.context, Locale.ENGLISH);

		final StringBuilder address = new StringBuilder();

		try {
			List<Address> addresses = geocoder.getFromLocation(point.latitude,
					point.longitude, 1);

			if (addresses.size() > 0) {
				for (int index = 0; index < addresses.get(0)
						.getMaxAddressLineIndex(); ++index) {
					address.append(addresses.get(0).getAddressLine(index));
					address.append(" ");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return this.cyrillicToLatin(address.toString());
	}

	private String cyrillicToLatin(String cyrillic) {
		String id = "Any-Latin;";
		String latin = Transliterator.getInstance(id).transform(cyrillic);
		return latin;
	}

	private void sendMarker(String address, LatLng point) {
		Log.i(TAG, address);
		final String[] addressSplitted = address.split(" "); 
		final String city = addressSplitted[addressSplitted.length - 1];
		address = address.replace(" " + city, "").replace("„", "").replace("“", "").replace("â", "q");
		final Marker marker = new Marker(point.longitude, point.latitude, address, city);
		
		
		final String locationJsonString = this.gson.toJson(marker);
		try {
			final RestServiceClient restServiceClient = new RestServiceClient(URLs.URL_POST_NEW_MARKER, locationJsonString, new HttpRequesterCallback() {
				
				@Override
				public void onResponse(String responseMessage) {
					Log.i(TAG, responseMessage);
				}
				
				@Override
				public void onError(String responseError) {
					// TODO Auto-generated method stub
					
				}
			});
			
			restServiceClient.addHeader("Content-Type", "application/json");
			restServiceClient.execute();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onLocationChanged(Location location) {
		final double latitude = location.getLatitude();
		final double longitude = location.getLongitude();

		final LatLng latLng = new LatLng(latitude, longitude);

		this.moveCamera(latLng);
	}
}
