package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.manager.BusyFlightManager;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jesjobom
 */
@RestController
public class BusyFlightApi {
    
    @Autowired
    private BusyFlightManager busyFlightManager;
    
    @RequestMapping(path = "/", method = RequestMethod.POST)
    public List<BusyFlightsResponse> requestAvailableFlights(@RequestBody BusyFlightsRequest req, HttpServletResponse response) throws IOException {
        if(!req.isValid()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), req.getValidationMessage());
            return null;
        }
        
        List<BusyFlightsResponse> flightsResponses = busyFlightManager.searchFlights(req);
        Collections.sort(flightsResponses);
        return flightsResponses;
    }
}
