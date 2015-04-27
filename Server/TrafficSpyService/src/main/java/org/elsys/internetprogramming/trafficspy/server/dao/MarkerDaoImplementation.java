package org.elsys.internetprogramming.trafficspy.server.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.elsys.internetprogramming.trafficspy.server.Marker;
import org.elsys.internetprogramming.trafficspy.server.controller.MarkerController;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class MarkerDaoImplementation implements MarkerDaoInterface {
	private Logger logger = Logger.getLogger(MarkerController.class.getName());
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addMarker(Marker marker) {
		this.sessionFactory.getCurrentSession().save(marker);
	}

	@Override
	public void deleteMarker(long markerId) {
		logger.info("DAO DELETE MARKER");
		this.sessionFactory.getCurrentSession().delete(this.getMarker(markerId));
	}

	@Override
	public Marker getMarker(long markerId) {
		return (Marker) this.sessionFactory.getCurrentSession().get(Marker.class, markerId);
	}

	@Override
	public List<Marker> getAllMarkers() {
		return this.sessionFactory.getCurrentSession().createQuery("from Marker ").list();
	}
}
