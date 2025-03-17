package com.cathay.coindesk.controller;

import com.cathay.coindesk.model.CoindeskResponse;
import com.cathay.coindesk.model.entity.Currency;
import com.cathay.coindesk.model.TransformedResponse;
import com.cathay.coindesk.repository.CurrencyRepository;
import com.cathay.coindesk.service.CoindeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/coindesk")
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;

    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping
    public TransformedResponse getTransformedData() {
        CoindeskResponse coindeskData = coindeskService.getCoindeskData();
        List<Currency> currencies = currencyRepository.findAll();

        TransformedResponse response = new TransformedResponse();
        response.setUpdatedTime(formatTime(coindeskData.getTime().getUpdatedISO()));

        List<TransformedResponse.CurrencyData> currencyDataList = new ArrayList<>();
        for (String code : coindeskData.getBpi().keySet()) {
            CoindeskResponse.CurrencyInfo info = coindeskData.getBpi().get(code);
            Currency currency = currencies.stream()
                    .filter(c -> c.getCode().equals(code))
                    .findFirst()
                    .orElse(null);
            String chineseName = currency != null ? currency.getChineseName() : "未知";

            TransformedResponse.CurrencyData data = new TransformedResponse.CurrencyData();
            data.setCode(code);
            data.setChineseName(chineseName);
            data.setRate(info.getRate());
            currencyDataList.add(data);
        }
        response.setCurrencies(currencyDataList);

        return response;
    }

    private String formatTime(String isoTime) {
        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = isoFormat.parse(isoTime);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return outputFormat.format(date);
        } catch (Exception e) {
            return isoTime; // 若轉換失敗，返回原始時間
        }
    }
}