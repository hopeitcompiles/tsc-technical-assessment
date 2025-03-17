package com.tsc.clients.controller;

import com.tsc.clients.dto.ClientCreateDTO;
import com.tsc.clients.dto.ClientDTO;
import com.tsc.clients.dto.ClientUpdateDTO;
import com.tsc.clients.serviceimp.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClients() {
        List<ClientDTO> people=clientService.getAll();
        return ResponseEntity.ok(people);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
        ClientDTO client=clientService.findById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientCreateDTO client) {
        ClientDTO clientDTO=this.clientService.save(client);
        return ResponseEntity.ok(clientDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDTO> createClient(@PathVariable Long id, @Valid @RequestBody ClientUpdateDTO client) {
        ClientDTO clientDTO=this.clientService.update(id, client);
        return ResponseEntity.ok(clientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable Long id) {
        ClientDTO clientDTO=this.clientService.delete(id);
        return ResponseEntity.ok(clientDTO);
    }
}
