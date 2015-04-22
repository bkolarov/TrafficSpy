package org.elsys.internetprogramming.trafficspy.server.service;

import java.util.List;

import org.elsys.internetprogramming.trafficspy.server.Marker;
import org.elsys.internetprogramming.trafficspy.server.dao.MarkerDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarkerServiceImplementation implements MarkerServiceInterface {
	@Autowired
	private MarkerDaoInterface markerDao;
	
	@Transactional
	public void addMarker(Marker marker) {
		this.markerDao.addMarker(marker);
	}

	@Transactional
	public void deleteMarker(int markerId) {
		this.markerDao.deleteMarker(markerId);
	}

	@Transactional
	public Marker getMarker(int markerId) {
		return this.markerDao.getMarker(markerId);
	}

	@Transactional
	public List<Marker> getAllMarkers() {
		return this.markerDao.getAllMarkers();
	}
}
