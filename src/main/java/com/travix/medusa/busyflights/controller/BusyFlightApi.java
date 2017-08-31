package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jesjobom
 */
@RestController
public class BusyFlightApi {
    
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public BusyFlightsResponse requestAvailableFlights(BusyFlightsRequest req, HttpServletResponse response) throws IOException {
        if(!req.isValid()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), req.getValidationMessage());
            return null;
        }
        
        return new BusyFlightsResponse();
    }
}
