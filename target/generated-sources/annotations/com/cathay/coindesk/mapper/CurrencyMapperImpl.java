package com.cathay.coindesk.mapper;

import javax.annotation.Generated;
import com.cathay.coindesk.dto.CurrencyDTO;
import org.example.model.entity.CurrencyEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-17T09:51:43+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_382 (Amazon.com Inc.)"
)
@Component
public class CurrencyMapperImpl implements CurrencyMapper {

    @Override
    public CurrencyDTO toDto(CurrencyEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CurrencyDTO currencyDTO = new CurrencyDTO();

        return currencyDTO;
    }

    @Override
    public CurrencyEntity toEntity(CurrencyDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CurrencyEntity currencyEntity = new CurrencyEntity();

        return currencyEntity;
    }
}
