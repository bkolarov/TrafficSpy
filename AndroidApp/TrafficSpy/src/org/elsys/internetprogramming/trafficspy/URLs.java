package org.elsys.internetprogramming.trafficspy;

public class URLs {
	public static final String DEFAULT_URL = "http://ec2-52-28-51-57.eu-central-1.compute.amazonaws.com:8181";
	public static final String URL_MARKERS = DEFAULT_URL + "/markers";
	public static final String URL_POST_NEW_MARKER = DEFAULT_URL + "/markers/new";
	public static final String URL_POST_CURRENT_LOCATION = DEFAULT_URL + "/markers/nearby";
	public static final String URL_LOGOUT = DEFAULT_URL + "/logout";
	public static final String URL_DELETE = URL_MARKERS + "/user/delete?id=<id>";
}
