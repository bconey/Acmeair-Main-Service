package com.ibm.api;

import org.eclipse.microprofile.metrics.annotation.Timed;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/")
public interface customerInterface {
  @GET
  @Path("/byid/{custid}")
  @Produces("text/plain")
  @Timed(name="com.acmeair.web.CustomerServiceRest.getCustomer", tags = "app=acmeair-customerservice-java")
  @RolesAllowed({"user"})
  public Response getCustomer(@PathParam("custid") String customerid);

  @POST
  @Path("/byid/{custid}")
  @Produces("text/plain")
  @Timed(name="com.acmeair.web.CustomerServiceRest.putCustomer", tags = "app=acmemair-customerservice-java")
  @RolesAllowed({"user"})
  public Response putCustomer(CustomerInfo customer, @PathParam("custid") String customerid );

  @GET
  public Response status();
}
