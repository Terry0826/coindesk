package com.cathay.coindesk.repository;

import com.cathay.coindesk.model.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}