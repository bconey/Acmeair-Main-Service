package com.ibm.api;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/booking")
public class bookingEndpoint {
   
  static bookingInterface booking;
  static {
    try{
    booking = RestClientBuilder.newBuilder().baseUrl(new URL("https://app-coney-0822-aabook.staging.us-east-1.c1.appflow.dev.ibmappdomain.cloud/booking")).build(bookingInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }
  @POST
  @Consumes({ "application/x-www-form-urlencoded" })
  @Path("/bookflights")
  @Produces("text/plain")
  @Timed(name = "com.acmeair.web.BookingServiceRest.bookFlights", tags = "app=bookingservice-java")
  @RolesAllowed({"user"})
  public Response bookFlights(@FormParam("userid") String userid,
      @FormParam("toFlightId") String toFlightId, 
      @FormParam("toFlightSegId") String toFlightSegId,
      @FormParam("retFlightId") String retFlightId, 
      @FormParam("retFlightSegId") String retFlightSegId,
      @FormParam("oneWayFlight") boolean oneWay) {
        return booking.bookFlights(userid, toFlightId, toFlightSegId, retFlightId, retFlightSegId, oneWay);
  }
  @GET
  @Path("/byuser/{user}")
  @Produces("text/plain")
  @Timed(name = "com.acmeair.web.bookFlights.BookingServiceRest.getBookingsByUser", tags = "app=bookingservice-java")
  @RolesAllowed({"user"})
  public Response getBookingsByUser(@PathParam("user") String userid) {
    return booking.getBookingsByUser(userid);
  }

  @POST
  @Consumes({ "application/x-www-form-urlencoded" })
  @Path("/cancelbooking")
  @Produces("text/plain")
  @Timed(name = "com.acmeair.web.bookFlights.BookingServiceRest.cancelBookingsByNumber", tags = "app=bookingservice-java")
  @RolesAllowed({"user"})
  public Response cancelBookingsByNumber(@FormParam("number") String number, 
      @FormParam("userid") String userid) {
        return booking.cancelBookingsByNumber(number, userid);
  }

  @GET
  public Response status() {
    return booking.status();
  }

  @GET
  @Path("/rewards/customerfailures")
  public Response customerFailures() {
    return booking.customerFailures();
  }

  @GET
  @Path("/rewards/flightfailures")
  public Response flightFailures() {
    return booking.flightFailures();
  }

  @GET
  @Path("/rewards/customersuccesses")
  public Response customerSucceses() {
    return booking.customerSucceses();
  }

  @GET
  @Path("/rewards/flightsuccesses")
  public Response flightSuccesseses() {
    return booking.flightSuccesseses();
  }

  @GET
  @Path("/audit")
  public Response audit() {
    return booking.audit();
  }
}
