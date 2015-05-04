package org.elsys.internetprogramming.trafficspy.server.marker.dao;

import java.util.List;

import org.elsys.internetprogramming.trafficspy.server.Marker;

public interface MarkerDaoInterface {
	public void addMarker(Marker marker);
	public void deleteMarker(long markerId);
	public Marker getMarker(long markerId);
	public List<Marker> getAllMarkers();
}
