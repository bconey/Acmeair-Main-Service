package com.ibm.api;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import jakarta.ws.rs.WebApplicationException;

// Payara and Helidon don't seem to support Date on a JAX-RS Param,
// so added this hack to convert to the Date.

public class DateParam {
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
  private static final int ZERO = 48;
  private Date date;
  
  public DateParam( String dateTime ) throws WebApplicationException {
    
      String dateOnly;

      if (dateTime.charAt(11) == ZERO) {
        // Assume format is EEE MMM dd 00:00:00 z yyyy from jmeter. Chop off the time + timezone.
        dateOnly = dateTime.substring(0,10) + " " + dateTime.substring(24,28);
      }
      else {
        // Assume format is EEE MMM dd yyyy from the browser.
        dateOnly = dateTime.substring(0,15);
      }

      LocalDate localDate = LocalDate.parse(dateOnly, formatter);
      date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  public Date getDate() {
    return date;
  } 
}
