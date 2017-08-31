package com.travix.medusa.busyflights.client;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author jesjobom
 */
@Service
public class ToughJetClient {
    
    public List<ToughJetResponse> requestFlights(ToughJetRequest req) {
        return Collections.EMPTY_LIST;
    }
}
