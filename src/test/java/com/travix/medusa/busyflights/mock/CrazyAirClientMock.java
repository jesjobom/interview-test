package com.travix.medusa.busyflights.mock;

import com.travix.medusa.busyflights.client.CrazyAirClient;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 *
 * @author jesjobom
 */
@Service
@Primary
public class CrazyAirClientMock extends CrazyAirClient {

    private List<CrazyAirResponse> crazyAirResponses = new ArrayList<>();
    
    @Override
    public List<CrazyAirResponse> requestFlights(CrazyAirRequest req) {
        return crazyAirResponses;
    }
    
    public void addResponse(CrazyAirResponse response) {
        crazyAirResponses.add(response);
    }
}
