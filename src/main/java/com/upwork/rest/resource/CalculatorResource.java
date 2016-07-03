package com.upwork.rest.resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.upwork.rest.entity.Result;


/**
 *
 * @author ThirupathiReddy V
 *
 */

@Path("/api")
public class CalculatorResource {

    /** The logger instance */
    private static final Log LOG = LogFactory.getLog(CalculatorResource.class);

    private final Date lastModified = new Date();

    @GET
    @Path("/add/{params: .*}")
    @Produces("application/json")
    public Response addMulti(@PathParam("params") List<PathSegment> params, @Context Request request) {

        final ResponseBuilder builder = request.evaluatePreconditions(lastModified);
        if (builder != null) {
            return builder.build();
        }

        LOG.info(String.format("performing addition "));

        BigDecimal result = new BigDecimal(0.0);

        for (final PathSegment seg : params) {
            try {
                result = result.add(new BigDecimal(seg.getPath()));
            } catch (final NumberFormatException numberFormatException) {
                // Ignore
            }
        }

        return Response.ok(new Result(result.doubleValue())).build();

    }

    @GET
    @Path("/sub/{a}/{b}")
    @Produces("application/json")
    public Response subTwo(@PathParam("a") double a, @PathParam("b") double b) {
        LOG.info(String.format("performing subtraction on two number %f- %f  ", a, b));

        return Response.status(200).entity(new Result(Double.valueOf(a - b))).build();
    }

    @GET
    @Path("/multi/{a}/{b}")
    @Produces("application/json")
    public Response multiply(@PathParam("a") double a, @PathParam("b") double b) {
        LOG.info(String.format("performing multiplication on two number %f * %f  ", a, b));
        return Response.status(200).entity(new Result(Double.valueOf(a * b))).build();
    }

    @GET
    @Path("/div/{a}/{b}")
    @Produces("application/json")
    public Response div(@PathParam("a") double a, @PathParam("b") double b) {

        LOG.info(String.format("performing division on two number %f / %f  ", a, b));

        if (b == 0) {
            LOG.info(String.format("Invalid division passed b = %f", b));
            return Response.status(200).entity(" Invalid input ").build();
        }
        return Response.status(200).entity(new Result(Double.valueOf(a / b))).build();
    }

}
