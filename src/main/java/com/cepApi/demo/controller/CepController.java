package com.cepApi.demo.controller;

import com.cepApi.demo.services.CepService;
import com.cepApi.demo.models.CepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/{cep}")
    public ResponseEntity<CepResponse> getCepInfo(@PathVariable("cep") String cep) {
        return ResponseEntity.ok(cepService.getCepInfo(cep));
    }
}
