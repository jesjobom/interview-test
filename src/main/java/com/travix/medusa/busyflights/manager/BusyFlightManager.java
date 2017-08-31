package com.travix.medusa.busyflights.manager;

import com.travix.medusa.busyflights.client.CrazyAirClient;
import com.travix.medusa.busyflights.client.ToughJetClient;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jesjobom
 */
@Service
public class BusyFlightManager {
    
    @Autowired
    private ToughJetClient toughJetClient;
    
    @Autowired
    private CrazyAirClient crazyAirClient;
    
    public List<BusyFlightsResponse> searchFlights(BusyFlightsRequest req) {
        List<BusyFlightsResponse> busyFlightsResponses = new ArrayList<>();
        busyFlightsResponses.addAll(searchCrazyAirFlights(req));
        busyFlightsResponses.addAll(searchToughJetFlights(req));
        return busyFlightsResponses;
    }
    
    /**
     * TODO Through a simple abstraction it'd be possible to search in all companies 
     * in a single step, by acquiring all registered beans of a base class.
     * 
     * @param req
     * @return 
     */
    private List<BusyFlightsResponse> searchCrazyAirFlights(BusyFlightsRequest req) {
        CrazyAirRequest crazyAirReq = new CrazyAirRequest();
        crazyAirReq.setOrigin(req.getDepartureDate());
        crazyAirReq.setDestination(req.getDestination());
        crazyAirReq.setDepartureDate(req.getDepartureDate());
        crazyAirReq.setReturnDate(req.getReturnDate());
        crazyAirReq.setPassengerCount(req.getNumberOfPassengers());
        
        List<CrazyAirResponse> crazyAirResponses = crazyAirClient.requestFlights(crazyAirReq);
        List<BusyFlightsResponse> busyFlightsResponses = crazyAirResponses.stream().map((t) -> {
            BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
            busyFlightsResponse.setAirline(t.getAirline());
            busyFlightsResponse.setArrivalDatel(t.getArrivalDate());
            busyFlightsResponse.setDeparture(t.getDestinationAirportCode());
            busyFlightsResponse.setDepartureDate(t.getDepartureDate());
            busyFlightsResponse.setDestination(t.getDestinationAirportCode());
            busyFlightsResponse.setFare(t.getPrice());
            
            busyFlightsResponse.setSupplier("CrazyJet");
            
            return busyFlightsResponse;
        }).collect(Collectors.toList());
        
        return busyFlightsResponses;
    }
    
    private List<BusyFlightsResponse> searchToughJetFlights(BusyFlightsRequest req) {
        ToughJetRequest toughJetReq = new ToughJetRequest();
        toughJetReq.setFrom(req.getDepartureDate());
        toughJetReq.setTo(req.getDestination());
        toughJetReq.setInboundDate(req.getDepartureDate());
        toughJetReq.setOutboundDate(req.getReturnDate());
        toughJetReq.setNumberOfAdults(req.getNumberOfPassengers());
        
        List<ToughJetResponse> toughJetResponses = toughJetClient.requestFlights(toughJetReq);
        List<BusyFlightsResponse> busyFlightsResponses = toughJetResponses.stream().map((t) -> {
            BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();
            busyFlightsResponse.setAirline(t.getCarrier());
            busyFlightsResponse.setArrivalDatel(t.getInboundDateTime());
            busyFlightsResponse.setDeparture(t.getDepartureAirportName());
            busyFlightsResponse.setDepartureDate(t.getOutboundDateTime());
            busyFlightsResponse.setDestination(t.getArrivalAirportName());
            busyFlightsResponse.setFare(t.getFinalPrice());
            
            busyFlightsResponse.setSupplier("ToughJet");
            
            return busyFlightsResponse;
        }).collect(Collectors.toList());
        
        return busyFlightsResponses;
    }
}
