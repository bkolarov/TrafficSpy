package org.elsys.internetprogramming.trafficspy;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
	private Map map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		RestServiceClient.COOKIES = getIntent().getStringExtra("cookie");
		RestServiceClient.USER_EMAIL = getIntent().getStringExtra("email");
		
		this.init();
		this.map.getMarkers();
	}

	private void init() {
		final GoogleMap googleMap = ((MapFragment) getFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		this.map = new Map(googleMap, this);
	}
	
	@Override
	protected void onPause() {
		//super.startService(new Intent(super.getApplicationContext(), LocationService.class));
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.stopService(new Intent(super.getApplicationContext(), LocationService.class));
		super.onResume();
	}
	
	@Override
	public void onBackPressed() {
		
		MyAlertDialog.createAlertDialog(this,
				"Are you sure you want to logout?", null, "Yes",
				new AlertDialogButtonOnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog) {
						logout();
					}
				}, "No", new AlertDialogButtonOnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog) {
						dialog.dismiss();
					}
				});
	}
	
	private void logout() {
		final Intent intent = new Intent(super.getApplicationContext(), LoginActivity.class);
		intent.putExtra("logout", true);
		super.stopService(new Intent(super.getApplicationContext(), LocationService.class));
		super.startActivity(intent);
		super.onBackPressed();
	}
}
