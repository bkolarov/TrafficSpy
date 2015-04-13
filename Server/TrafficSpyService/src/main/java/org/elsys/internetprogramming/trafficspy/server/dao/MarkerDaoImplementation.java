package org.elsys.internetprogramming.trafficspy.server.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.elsys.internetprogramming.trafficspy.server.Marker;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class MarkerDaoImplementation implements MarkerDaoInterface {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addMarker(Marker marker) {
		this.sessionFactory.getCurrentSession().save(marker);
	}

	@Override
	public void deleteMarker(int markerId) {
		this.sessionFactory.getCurrentSession().delete(this.getMarker(markerId));
	}

	@Override
	public Marker getMarker(int markerId) {
		return (Marker) this.sessionFactory.getCurrentSession().get(Marker.class, markerId);
	}

	@Override
	public List<Marker> getAllMarkers() {
		return this.sessionFactory.getCurrentSession().createQuery("from Marker ").list();
	}
}
