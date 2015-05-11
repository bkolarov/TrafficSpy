package org.elsys.internetprogramming.trafficspy.server.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.elsys.internetprogramming.trafficspy.server.marker.controller.MarkerController;
import org.elsys.internetprogramming.trafficspy.server.users.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImplementation implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	private Logger logger = Logger.getLogger(MarkerController.class.getName());
	@SuppressWarnings("unchecked")
	public User findByEmail(String username) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
				.createQuery("from User where email = :email")
				.setString("email",username)
				.list();
		logger.info(username);

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
}
