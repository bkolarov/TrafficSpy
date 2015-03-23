package org.elsys.internetprogramming.trafficspy;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

public class LocationService extends Service {
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longtitude";
	
	private final String TAG = "LocationService";
	private final long MINIMUM_TIME_BETWEEN_UPDATES = 1000 * 2;
	private final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 0;

	private LocationManager locationManager;
	private String locationProvider;
	
	private Intent intentSendDataToActivity;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		this.init();
		final LocationListener locationListener = new LocationListener();

		if (this.locationManager
				.isProviderEnabled(this.locationProvider = LocationManager.GPS_PROVIDER)) {
			this.requestLocationUpdates(this.locationProvider, locationListener);
		} else if (this.locationManager
				.isProviderEnabled(this.locationProvider = LocationManager.NETWORK_PROVIDER)) {
			this.requestLocationUpdates(this.locationProvider, locationListener);
		} else {
			this.showToast("No location provider available.",
					Toast.LENGTH_SHORT);
		}

		if (this.locationProvider != null) {
			final Location location = this.locationManager
					.getLastKnownLocation(this.locationProvider);

			if (location != null) {
				Log.i(TAG, "longtitude: " + location.getLongitude()
						+ " latitude: " + location.getLatitude());
				sendLocationToActivity(location);
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
		this.intentSendDataToActivity = new Intent(Map.BROADCAST_RECEIVER_BANE);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private void sendLocationToActivity(Location location) {
		this.intentSendDataToActivity.putExtra(LocationService.LONGITUDE, location.getLongitude());
		this.intentSendDataToActivity.putExtra(LocationService.LATITUDE, location.getLatitude());
		
		LocalBroadcastManager.getInstance(this).sendBroadcast(intentSendDataToActivity);
	}
	
	private class LocationListener implements android.location.LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			Log.i(TAG, "longtitude: " + location.getLongitude() + " latitude: "
					+ location.getLatitude());
			sendLocationToActivity(location);
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
		Toast.makeText(super.getApplicationContext(), message, duration).show();
	}
}
