package org.elsys.internetprogramming.trafficspy.server.service;

import java.util.List;

import org.elsys.internetprogramming.trafficspy.server.Marker;

public interface MarkerServiceInterface {
	public void addMarker(Marker marker);
	public void deleteMarker(int markerId);
	public Marker getMarker(int markerId);
	public List<Marker> getAllMarkers();
}
