package com.upwork.rest.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.upwork.rest.resource.CalculatorResource;

/**
 * This class gets invoked by Servlet 3.x based container Ex: Tomcat7 and above.
 *
 *  no web.xml required .
 *
 * @author ThirupathiReddy
 *
 */

@ApplicationPath("/calculator")
public class ServerConfig extends Application {

    /** The logger instance */
    private static final Log LOG = LogFactory.getLog(ServerConfig.class);


    @Override
    public Set<Class<?>> getClasses() {

        LOG.info("--------------- Loading resources ---------------");
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(CalculatorResource.class);
        return classes;

    }


}