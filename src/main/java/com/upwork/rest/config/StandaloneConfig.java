package com.upwork.rest.config;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.servlet.GrizzlyWebContainerFactory;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

import com.upwork.rest.resource.CalculatorResource;

/**
 * This class creates simple embedded server for the JAX-RS end points.
 *
 * @author ThirupathiReddy V
 *
 */
public class StandaloneConfig {

    /** The logger instance */
    private static final Log LOG = LogFactory.getLog(StandaloneConfig.class);

    private static final URI BASE_URI = URI.create("http://localhost:8080/calculator");

    public static final String WADL_PATH = "application.wadl";

    public static void main(String[] args) {
        try {
            LOG.info("Starting the server .....");

            final Map<String, String> initParams = new HashMap<String, String>();

            initParams.put(ServerProperties.SUBRESOURCE_LOCATOR_CACHE_SIZE, "128");
            initParams.put(ServerProperties.SUBRESOURCE_LOCATOR_CACHE_JERSEY_RESOURCE_ENABLED, "true");
            initParams.put(ServerProperties.SUBRESOURCE_LOCATOR_CACHE_AGE, "1000");

            initParams.put(ServerProperties.PROVIDER_PACKAGES, CalculatorResource.class.getPackage().getName());

            final HttpServer server = GrizzlyWebContainerFactory.create(BASE_URI, ServletContainer.class, initParams);

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    LOG.info("Shutting down the server gracefully....");
                    server.shutdownNow();
                }
            }));

            LOG.info(String.format("Application started.%nTry out %s/%s%nStop the application using CTRL+C", BASE_URI, WADL_PATH));

            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            LOG.error("Error while starting the server ...", ex);
        }
    }
}
