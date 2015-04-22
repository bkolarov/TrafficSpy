package org.elsys.internetprogramming.trafficspy.server;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	private static Logger logger = Logger.getLogger(Application.class.getName());
	
	
	public static void main(String[] args) {
		 	logger.info("SPRING VERSION: " + SpringVersion.getVersion());
	        SpringApplication.run(Application.class, args);
	    }
}
