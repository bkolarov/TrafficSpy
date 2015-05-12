package org.elsys.internetprogramming.trafficspy;

public class Marker {
	private double longitude;
	private double latitude;
	private String address;
	private String city;
	private String userEmail;
	
	public Marker() {
		// Empty constructor
	}
	
	public Marker(double longitude, double latitude, String address, String city, String userEmail) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.city = city;
		this.userEmail = userEmail;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return this.city;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
