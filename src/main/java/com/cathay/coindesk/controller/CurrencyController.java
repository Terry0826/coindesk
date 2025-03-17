package com.cathay.coindesk.controller;

import com.cathay.coindesk.model.entity.Currency;
import com.cathay.coindesk.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @PostMapping
    public Currency createCurrency(@RequestBody Currency currency) {
        return currencyRepository.save(currency);
    }

    @PutMapping("/{code}")
    public Currency updateCurrency(@PathVariable String code, @RequestBody Currency currency) {
        currency.setCode(code);
        return currencyRepository.save(currency);
    }

    @DeleteMapping("/{code}")
    public void deleteCurrency(@PathVariable String code) {
        currencyRepository.deleteById(code);
    }
}