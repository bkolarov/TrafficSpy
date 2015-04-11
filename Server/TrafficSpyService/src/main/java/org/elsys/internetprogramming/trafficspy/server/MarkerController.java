package org.elsys.internetprogramming.trafficspy.server;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import scala.annotation.serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
public class MarkerController {
	private final AtomicLong id = new AtomicLong();
	private Logger logger = Logger.getLogger(MarkerController.class.getName());
	
	@RequestMapping(value="/markers", method=RequestMethod.GET)
	public @ResponseBody String getMarkers(@RequestParam(value="city", defaultValue="") String city) {
		logger.info("HANDLE GET REQUEST");
		return "{\"id\":\"1\"}";
	}
	
	@RequestMapping(value="/markers/new", method=RequestMethod.POST)
	public @ResponseBody Marker putMarker(@RequestBody Marker marker) {
		logger.info("HANDLE POST REQUEST");
		return marker;
	}
	
}
