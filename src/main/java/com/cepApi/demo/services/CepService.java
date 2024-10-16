package com.cepApi.demo.services;

import com.cepApi.demo.models.CepResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    @Value("${external.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public CepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CepResponse getCepInfo(String cep) {
        String url = String.format("%s/%s/json", apiUrl, cep);
        return this.restTemplate.getForObject(url, CepResponse.class);
    }
}
