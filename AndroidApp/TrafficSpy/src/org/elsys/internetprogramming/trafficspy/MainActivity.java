package org.elsys.internetprogramming.trafficspy;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

public class MainActivity extends Activity {
	private Map map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.init();
		
		final Intent intent = new Intent(this, LocationService.class);
		super.startService(intent);
		
		LocalBroadcastManager.getInstance(this).registerReceiver(map, new IntentFilter(Map.BROADCAST_RECEIVER_BANE));
	}

	private void init() {
		final GoogleMap googleMap = ((MapFragment) getFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		this.map = new Map(googleMap, this);
	}
	
	@Override
	protected void onPause() {
		LocalBroadcastManager.getInstance(this).unregisterReceiver(map);
		super.onPause();
	}
}
