package com.ibm.api;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

public class flightEndpoint {

  static flightInterface flight;
  static {
    try{
    flight = RestClientBuilder.newBuilder().baseUrl(new URL("https://app-coney-0822-aaflight.staging.us-east-1.c1.appflow.dev.ibmappdomain.cloud/flight")).build(flightInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }
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
      @FormParam("oneWay") boolean oneWay) {
        
    return flight.getTripFlights(fromAirport,toAirport,fromDate,returnDate,oneWay);
  }

  @POST
  @Path("/getrewardmiles")
  @Consumes({"application/x-www-form-urlencoded"})
  @Produces("application/json")
  @Timed(name = "com.acmeair.web.FlightServiceRest.getRewardsMiles", tags = "app=acmeair-flightservice-java")
  public MilesResponse getRewardMiles(
      @FormParam("flightSegment") String segmentId){
        return flight.getRewardMiles(segmentId);
      }

  @GET
  public Response status() {
    return flight.status();
  }

  @GET
  @Path("/searches")
  public Response searches() {
    return flight.searches();
  }

  @GET
  @Path("/successes")
  public Response successes() {
    return flight.successes();
  }

  @GET
  @Path("/successratio")
  public Response successratio() {
      return flight.successratio();
    }
  
  @GET
  @Path("/audit")
  public Response audit() {
    return flight.audit();
  }
}
