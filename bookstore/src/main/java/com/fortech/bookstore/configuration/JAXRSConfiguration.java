package com.fortech.bookstore.configuration;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fortech.bookstore.rest.BookEndpoint;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@ApplicationPath("api")
public class JAXRSConfiguration extends Application {

	public JAXRSConfiguration() {
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setTitle("Swagger");
		beanConfig.setDescription("Description of book store endpoints");
		beanConfig.setVersion("1.0.0");
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/bookstore/api");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setResourcePackage("com.fortech.bookstore.rest");
		beanConfig.setScan(true);
	}

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();

		// Swagger classes
		resources.add(SwaggerSerializers.class);
		resources.add(ApiListingResource.class);

		// App resources
		resources.add(BookEndpoint.class);

		return resources;
	}

}
