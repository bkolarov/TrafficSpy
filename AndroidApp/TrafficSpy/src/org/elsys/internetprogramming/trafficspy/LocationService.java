package org.elsys.internetprogramming.trafficspy;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;

public class LocationService extends Service implements MyLocationListener, RestClientCallback {
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longtitude";
	
	private final String TAG = "LocationService";
	private LocationListener locationListener;
	private RestServiceClient restServiceClient;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		this.init();
	}

	private void init() {
		this.locationListener = new LocationListener(
				super.getApplicationContext(), this);
		this.restServiceClient = new RestServiceClient(this);
	}

	@Override
	public void onDestroy() {
		this.locationListener.stopUpdates();
		super.onDestroy();
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.d(TAG, "longitude: " + location.getLongitude() + " latitude: "
				+ location.getLatitude());
		
		final double latitude = location.getLatitude();
		final double longitude = location.getLongitude();
		
		String address = Map.getPointAddress(super.getApplicationContext(), new LatLng(latitude, longitude));
		final String[] addressSplitted = address.split(" ");
		final String city = addressSplitted[addressSplitted.length - 1];
		address = address.replace(" " + city, "").replace("„", "")
				.replace("“", "").replace("â", "q");
		final Marker marker = new Marker(longitude, latitude,
				address, city);
		if (this.restServiceClient != null && !this.restServiceClient.isExecuting()) {
			this.restServiceClient.doPost(URLs.URL_POST_CURRENT_LOCATION, new Gson().toJson(marker));
		}
	}

	@Override
	public void onResponse(String response) {
		Log.i(TAG, response);
		
	}
}
