package com.cathay.coindesk;

import com.cathay.coindesk.model.CoindeskResponse;
import com.cathay.coindesk.model.entity.Currency;
import com.cathay.coindesk.repository.CurrencyRepository;
import com.cathay.coindesk.service.CoindeskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CoindeskApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurrencyRepository currencyRepository;

    @MockBean
    private CoindeskService coindeskService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        currencyRepository.deleteAll();
        currencyRepository.save(new Currency("USD", "美元"));
    }

    @Test
    void testGetAllCurrencies() throws Exception {
        mockMvc.perform(get("/currencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value("USD"))
                .andExpect(jsonPath("$[0].chineseName").value("美元"));
    }

    @Test
    void testCreateCurrency() throws Exception {
        Currency newCurrency = new Currency("EUR", "歐元");
        mockMvc.perform(post("/currencies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCurrency)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("EUR"))
                .andExpect(jsonPath("$.chineseName").value("歐元"));
    }

    @Test
    void testUpdateCurrency() throws Exception {
        Currency updatedCurrency = new Currency("USD", "美金");
        mockMvc.perform(put("/currencies/USD")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCurrency)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("USD"))
                .andExpect(jsonPath("$.chineseName").value("美金"));
    }

    @Test
    void testDeleteCurrency() throws Exception {
        mockMvc.perform(delete("/currencies/USD"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/currencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testGetCoindeskData() throws Exception {
        CoindeskResponse mockResponse = new CoindeskResponse();
        CoindeskResponse.Time time = new CoindeskResponse.Time();
        time.setUpdatedISO("2024-09-02T07:07:20+00:00");
        mockResponse.setTime(time);
        Map<String, CoindeskResponse.CurrencyInfo> bpi = new HashMap<>();
        CoindeskResponse.CurrencyInfo usdInfo = new CoindeskResponse.CurrencyInfo();
        usdInfo.setCode("USD");
        usdInfo.setRate("57756.298");
        bpi.put("USD", usdInfo);
        mockResponse.setBpi(bpi);

        when(coindeskService.getCoindeskData()).thenReturn(mockResponse);

        mockMvc.perform(get("/coindesk"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.updatedTime").value("2024/09/02 07:07:20"))
                .andExpect(jsonPath("$.currencies[0].code").value("USD"))
                .andExpect(jsonPath("$.currencies[0].rate").value("57756.298"));
    }
}