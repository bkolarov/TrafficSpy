package org.elsys.internetprogramming.trafficspy.server.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.elsys.internetprogramming.trafficspy.server.Marker;
import org.elsys.internetprogramming.trafficspy.server.service.MarkerServiceImplementation;
import org.elsys.internetprogramming.trafficspy.server.service.MarkerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
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
	public @ResponseBody Marker addMarker(@RequestBody Marker marker) {
		logger.info("HANDLE POST REQUEST");
		
		this.markerService.addMarker(marker);
		return marker;
	}
	
}