package com.travix.medusa.busyflights.client;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jesjobom
 */
@Service
public class CrazyAirClient {

    @Value("${crazyair.url:#{null}}")
    private String url;
    
    public List<CrazyAirResponse> requestFlights(CrazyAirRequest req) {
        RestTemplate restTemplate = new RestTemplate();
        CrazyAirResponse[] responses = restTemplate.postForObject(url, req, CrazyAirResponse[].class);
        return Arrays.asList(responses);
    }
}
