package com.affinipay.assignment.application;

import com.affinipay.assignment.configuration.TimeDeltaConfiguration;
import com.affinipay.assignment.resource.TimeDeltaResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TimeDeltaApplication extends Application<TimeDeltaConfiguration> {
	public static void main(String[] args) throws Exception {
		new TimeDeltaApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<TimeDeltaConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(TimeDeltaConfiguration configuration, Environment environment) {
		final TimeDeltaResource resource = new TimeDeltaResource();
		environment.jersey().register(resource);
	}

}