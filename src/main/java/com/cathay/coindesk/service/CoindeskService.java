package com.cathay.coindesk.service;

import com.cathay.coindesk.model.CoindeskResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoindeskService {

    private final RestTemplate restTemplate;

    public CoindeskService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CoindeskResponse getCoindeskData() {
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        return restTemplate.getForObject(url, CoindeskResponse.class);
    }
}