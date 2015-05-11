package org.elsys.internetprogramming.trafficspy.server.users.dao;

import org.elsys.internetprogramming.trafficspy.server.users.model.User;

public interface UserDao {
	public User findByEmail(String username);
}
