package com.travix.medusa.busyflights.client;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
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
public class ToughJetClient {
    
    @Value("${toughjet.url:#{null}}")
    private String url;
    
    public List<ToughJetResponse> requestFlights(ToughJetRequest req) {
        RestTemplate restTemplate = new RestTemplate();
        ToughJetResponse[] responses = restTemplate.postForObject(url, req, ToughJetResponse[].class);
        return Arrays.asList(responses);
    }
}
