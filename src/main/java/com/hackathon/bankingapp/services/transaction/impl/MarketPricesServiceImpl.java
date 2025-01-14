package com.hackathon.bankingapp.services.transaction.impl;

import com.hackathon.bankingapp.exceptions.ApiException;
import com.hackathon.bankingapp.services.transaction.MarketPricesService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MarketPricesServiceImpl implements MarketPricesService {


    private final RestTemplate restTemplate;


    @Override
    public Map<String, BigDecimal> getAssetsPrice() {

        ParameterizedTypeReference<Map<String, BigDecimal>> responseType =
                new ParameterizedTypeReference<>() {
                };

        RequestEntity<Void> request = RequestEntity.get("https://faas-lon1-917a94a7.doserverless.co/api/v1/web/fn-e0f31110-7521-4cb9-86a2-645f66eefb63/default/market-prices-simulator")
                .accept(MediaType.APPLICATION_JSON).build();

        return restTemplate.exchange(request, responseType)
                .getBody();
    }

    @Override
    public BigDecimal getAssetPrice(String symbol) {
        Map<String, BigDecimal> assetsPrice = getAssetsPrice();

        if (assetsPrice.containsKey(symbol)) {
            return assetsPrice.get(symbol);
        }

        throw new ApiException("Asset not found", HttpStatus.NOT_FOUND);
    }
}
