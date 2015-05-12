package org.elsys.internetprogramming.trafficspy;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LocationListener implements android.location.LocationListener {
	private final String TAG = getClass().getSimpleName();
	private final long MINIMUM_TIME_BETWEEN_UPDATES = 1000 * 10;
	private final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 0;

	private LocationManager locationManager;
	private MyLocationListener locationListener;
	private String locationProvider;

	public LocationListener(Context context, MyLocationListener locationListener) {
		this.locationListener = locationListener;
		this.locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		if (this.locationManager
				.isProviderEnabled(this.locationProvider = LocationManager.GPS_PROVIDER)) {
			this.requestLocationUpdates(this.locationProvider);
		} else if (this.locationManager
				.isProviderEnabled(this.locationProvider = LocationManager.NETWORK_PROVIDER)) {
			this.requestLocationUpdates(this.locationProvider);
		} else {
			Toast.makeText(context, "No location provider available.",
					Toast.LENGTH_SHORT).show();
		}

		if (this.locationProvider != null) {
			final Location location = this.locationManager
					.getLastKnownLocation(this.locationProvider);

			if (location != null) {
				this.locationListener.onLocationChanged(location);
			}
		}
	}

	private void requestLocationUpdates(String provider) {
		this.locationManager.requestLocationUpdates(provider,
				this.MINIMUM_TIME_BETWEEN_UPDATES,
				this.MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, this);
	}

	@Override
	public void onLocationChanged(Location location) {
		this.locationListener.onLocationChanged(location);
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

	public void stopUpdates() {
		this.locationManager.removeUpdates(this);
	}
	
}
