package com.travix.medusa.busyflights.client;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author jesjobom
 */
@Service
public class CrazyAirClient {

    public List<CrazyAirResponse> requestFlights(CrazyAirRequest req) {
        return Collections.EMPTY_LIST;
    }
}
