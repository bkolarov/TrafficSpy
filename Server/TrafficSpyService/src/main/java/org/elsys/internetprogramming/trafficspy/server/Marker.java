package org.elsys.internetprogramming.trafficspy.server;

public class Marker {
	private long id;
	private double longitude;
	private double latitude;
	//{"id":1,"longitude":4.2684,"latitude":23.18968,"address":"ул. Горни Долни Бастун"}
	private String address;

	public Marker() {
		
	}
	
	public Marker(long id, double longitude, double latitude, String address) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

}
