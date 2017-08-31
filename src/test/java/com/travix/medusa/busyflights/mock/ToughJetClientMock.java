package com.travix.medusa.busyflights.mock;

import com.travix.medusa.busyflights.client.ToughJetClient;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
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
public class ToughJetClientMock extends ToughJetClient {

    private List<ToughJetResponse> toughJetResponses = new ArrayList<>();
    
    @Override
    public List<ToughJetResponse> requestFlights(ToughJetRequest req) {
        return toughJetResponses;
    }
    
    public void addResponse(ToughJetResponse response) {
        toughJetResponses.add(response);
    }
}
