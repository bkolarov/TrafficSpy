package org.elsys.internetprogramming.trafficspy.server.marker.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.elsys.internetprogramming.trafficspy.server.Marker;
import org.elsys.internetprogramming.trafficspy.server.marker.service.MarkerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MarkerController {
	
	private Logger logger = Logger.getLogger(MarkerController.class.getName());
	
	@Autowired
	private MarkerServiceInterface markerService;
	
	@RequestMapping(value="/markers", method=RequestMethod.GET)
	public @ResponseBody List<Marker> getMarkers(@RequestParam(value="city", defaultValue="") String city) {
		logger.info("HANDLE GET REQUEST");
		
		return this.markerService.getAllMarkers();
	}
	
	@RequestMapping(value="/markers/new", method=RequestMethod.POST)
	@Secured("ROLE_USER")
	public @ResponseBody Marker addMarker(@RequestBody Marker marker) {
		logger.info("HANDLE POST REQUEST");
		this.markerService.addMarker(marker);
		
		return marker;
	}
	
	@RequestMapping(value="/markers/delete", method=RequestMethod.DELETE)
	@Secured("ROLE_ADMIN")
	public @ResponseBody void deleteMarker(@RequestParam(value="id") String id) {
		logger.info("HANDLE DELETE REQUEST");
		logger.info(id);
		
		this.markerService.deleteMarker(Long.parseLong(id));
	}
	
	@RequestMapping(value="/markers/user/delete", method=RequestMethod.DELETE)
	@Secured("ROLE_USER")
	public @ResponseBody void deleteMarkerFromUser(@RequestParam(value="id") String id) {
		this.markerService.deleteMarker(Long.parseLong(id));
	}
	
	@RequestMapping(value="/admin/map")
	public String trafficSpy() {
		logger.info("HANDLE MAP");
		return "index";
	}
	
	@RequestMapping(value="/admin")
	public String admin() {
		return "admin";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
}
