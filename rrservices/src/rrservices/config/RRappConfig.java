package rrservices.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class RRappConfig extends ResourceConfig {
	
	public RRappConfig () {
		packages("rrservices.services");
	}
	
}
