package org.elsys.internetprogramming.trafficspy.server;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Marker")
public class Marker {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long markerId;
	@Column
	private double longitude;
	@Column
	private double latitude;
	@Column
	private String address;
	@Column
	private String city;
	@Column
	private String userEmail;
	
	public Marker() {
		// Empty constructor
	}
	
	public Marker(long id, double longitude, double latitude, String address, String city, String userEmail) {
		this.markerId = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.city = city;
		this.userEmail = userEmail;
	}

	public long getId() {
		return markerId;
	}

	public void setId(long id) {
		this.markerId = id;
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
