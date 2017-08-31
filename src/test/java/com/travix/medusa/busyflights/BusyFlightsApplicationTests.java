package com.travix.medusa.busyflights;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.mock.CrazyAirClientMock;
import com.travix.medusa.busyflights.mock.ToughJetClientMock;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BusyFlightsApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private ToughJetClientMock toughJetClientMock;
    
    @Autowired
    private CrazyAirClientMock crazyAirClientMock;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;
    
    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void emptyRequest() throws Exception {
        mockMvc.perform(post("/")
                .contentType(contentType))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validRequest() throws Exception {
        
        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setDepartureDate("2017-08-20");
        busyFlightsRequest.setReturnDate("2017-08-30");
        busyFlightsRequest.setOrigin("BSB");
        busyFlightsRequest.setDestination("AMS");
        busyFlightsRequest.setNumberOfPassengers(2);
        
        mockMvc.perform(post("/")
                .content(mapper.writeValueAsString(busyFlightsRequest))
                .contentType(contentType))
                .andExpect(status().isOk());
    }
    
    @Test
    public void validRequestReturningResults() throws Exception {
        
        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setDepartureDate("2017-08-20");
        busyFlightsRequest.setReturnDate("2017-08-30");
        busyFlightsRequest.setOrigin("BSB");
        busyFlightsRequest.setDestination("AMS");
        busyFlightsRequest.setNumberOfPassengers(2);
        
        CrazyAirResponse crazyAirResponse = new CrazyAirResponse();
        crazyAirResponse.setAirline("Airline Name");
        crazyAirResponse.setArrivalDate("2017-08-30 12:00:00");
        crazyAirResponse.setCabinclass("E");
        crazyAirResponse.setDepartureAirportCode("BSB");
        crazyAirResponse.setDepartureDate("2017-08-20 12:00:00");
        crazyAirResponse.setDestinationAirportCode("AMS");
        crazyAirResponse.setPrice(2500.50);
        crazyAirClientMock.addResponse(crazyAirResponse);
        
        ToughJetResponse toughJetResponse = new ToughJetResponse();
        toughJetResponse.setArrivalAirportName("AMS");
        toughJetResponse.setBasePrice(3000);
        toughJetResponse.setTax(100);
        toughJetResponse.setDiscount(20);
        toughJetResponse.setCarrier("Carrier Name");
        toughJetResponse.setDepartureAirportName("BSB");
        toughJetResponse.setInboundDateTime("2017-08-20 08:00:00");
        toughJetResponse.setOutboundDateTime("2017-08-30 23:00:00");
        toughJetClientMock.addResponse(toughJetResponse);
        
        MvcResult result = mockMvc.perform(post("/")
                .content(mapper.writeValueAsString(busyFlightsRequest))
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andReturn();
        
        Collection responses = mapper.readValue(result.getResponse().getContentAsString(), TypeFactory.defaultInstance().constructCollectionType(List.class, BusyFlightsResponse.class));
        
        Assert.assertEquals(2, responses.size());
    }
}
