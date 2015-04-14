package org.elsys.internetprogramming.trafficspy;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
	private Map map;

	@Override
	protected void onResume() {
		super.stopService(new Intent(super.getApplicationContext(), LocationService.class));
		super.onResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.init();
	}

	private void init() {
		final GoogleMap googleMap = ((MapFragment) getFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		this.map = new Map(googleMap, this);
	}
	
	@Override
	protected void onPause() {
		super.startService(new Intent(super.getApplicationContext(), LocationService.class));
		super.onPause();
	}
}