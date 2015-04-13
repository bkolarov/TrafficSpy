package org.elsys.internetprogramming.trafficspy.server.dao;

import java.util.List;

import org.elsys.internetprogramming.trafficspy.server.Marker;

public interface MarkerDaoInterface {
	public void addMarker(Marker marker);
	public void deleteMarker(int markerId);
	public Marker getMarker(int markerId);
	public List<Marker> getAllMarkers();
}
