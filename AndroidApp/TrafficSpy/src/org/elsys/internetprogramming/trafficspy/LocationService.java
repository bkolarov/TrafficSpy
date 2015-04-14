package org.elsys.internetprogramming.trafficspy;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;

public class LocationService extends Service implements MyLocationListener {
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longtitude";

	private final String TAG = "LocationService";
	private LocationListener locationListener;

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
	}

	@Override
	public void onDestroy() {
		this.locationListener.stopUpdates();
		super.onDestroy();
	}

	@Override
	public void onLocationChanged(Location location) {
		Log.d(TAG, "longtitude: " + location.getLongitude() + " latitude: "
				+ location.getLatitude());
	}
}
