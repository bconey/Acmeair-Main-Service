package com.ibm.api;

import org.eclipse.microprofile.metrics.annotation.Timed;

import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

public interface flightInterface {
  @POST
  @Path("/queryflights")
  @Consumes({"application/x-www-form-urlencoded"})
  @Produces("application/json")
  @Timed(name = "com.acmeair.web.FlightServiceRest.getTripFlights", tags = "app=acmeair-flightservice-java")
  public JsonObject getTripFlights(
      @FormParam("fromAirport") String fromAirport,
      @FormParam("toAirport") String toAirport,
      @FormParam("fromDate") DateParam fromDate,
      @FormParam("returnDate") DateParam returnDate,
      @FormParam("oneWay") boolean oneWay
      );
  @POST
  @Path("/getrewardmiles")
  @Consumes({"application/x-www-form-urlencoded"})
  @Produces("application/json")
  @Timed(name = "com.acmeair.web.FlightServiceRest.getRewardsMiles", tags = "app=acmeair-flightservice-java")
  public MilesResponse getRewardMiles(
      @FormParam("flightSegment") String segmentId
      );
    
  @GET
  public Response status();

  @GET
  @Path("/searches")
  public Response searches();

  @GET
  @Path("/successes")
  public Response successes();

  @GET
  @Path("/successratio")
  public Response successratio();

  @GET
  @Path("/audit")
  public Response audit();
}
