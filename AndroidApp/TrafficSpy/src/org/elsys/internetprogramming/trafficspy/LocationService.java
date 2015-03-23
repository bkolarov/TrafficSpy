package org.elsys.internetprogramming.trafficspy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LocationService extends Service {
	private final String TAG = "LocationService";
	private final long MINIMUM_TIME_BETWEEN_UPDATES = 1000 * 2;
	private final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 0;

	private LocationManager locationManager;
	
	private String locationProvider;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		this.init();
		final LocationListener locationListener = new LocationListener();
		
		if (this.locationManager.isProviderEnabled(this.locationProvider = LocationManager.GPS_PROVIDER)) {
			this.requestLocationUpdates(this.locationProvider,
					locationListener);
		} else if (this.locationManager.isProviderEnabled(this.locationProvider = LocationManager.NETWORK_PROVIDER)) {
			this.requestLocationUpdates(this.locationProvider,
					locationListener);
		} else {
			this.showToast("No location provider available.", Toast.LENGTH_SHORT);
		}
		
		if (this.locationProvider != null) {
			final Location location = this.locationManager.getLastKnownLocation(this.locationProvider);
			
			if (location != null) {
				Log.i(TAG, "longtitude: " + location.getLongitude() + " latitude: " + location.getLatitude());
			}
		}
		
	}

	private void requestLocationUpdates(String provider,
			LocationListener locationListener) {
		this.locationManager.requestLocationUpdates(provider,
				this.MINIMUM_TIME_BETWEEN_UPDATES,
				this.MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
	}

	private void init() {
		this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private class LocationListener implements android.location.LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			Log.i(TAG, "longtitude: " + location.getLongitude() + " latitude: " + location.getLatitude());

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

	}
	
	
	private void showToast(String message, int duration) {
		Toast.makeText(getApplicationContext(), message, duration).show();;
	}
}
