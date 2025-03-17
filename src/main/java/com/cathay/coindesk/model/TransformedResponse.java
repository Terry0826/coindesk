package com.cathay.coindesk.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Setter
@Getter
public class TransformedResponse {
    private String updatedTime;
    private List<CurrencyData> currencies;

    @Setter
    @Getter
    public static class CurrencyData {
        private String code;
        private String chineseName;
        private String rate;

    }
}