package com.tsc.accounts.service;

import com.tsc.accounts.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-client",url = "http://localhost:8081/clientes")
public interface IClientService {
    @GetMapping("/{id}")
    Client getClient(@PathVariable Long id);
}
