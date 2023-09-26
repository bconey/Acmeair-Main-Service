package com.ibm.api;

import org.eclipse.microprofile.metrics.annotation.Timed;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/")
public interface authInterface {
  @POST
  @Consumes({ "application/x-www-form-urlencoded" })
  @Produces("text/plain")
  @Path("/login")
  @Timed(name = "com.acmeair.web.AuthServiceRest.login", tags = "app=acmeair-authservice-java")
  public Response login(@FormParam("login") String login, @FormParam("password") String password);

  @GET
  @Produces("application/json")
  @Path("/getJwk")
  public Response getJwk();

  @GET
  public Response status();
}
