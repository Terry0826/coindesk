package com.cathay.coindesk.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Setter
@Getter
public class CoindeskResponse {
    private Time time;
    private String disclaimer;
    private String chartName;
    private Map<String, CurrencyInfo> bpi;

    @Setter
    @Getter
    public static class Time {
        private String updated;
        private String updatedISO;
        private String updateduk;

    }
    @Setter
    @Getter
    public static class CurrencyInfo {
        private String code;
        private String rate;

    }
}