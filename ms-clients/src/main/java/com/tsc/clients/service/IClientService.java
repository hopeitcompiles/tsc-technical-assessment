package com.tsc.clients.service;

import com.tsc.clients.dto.ClientCreateDTO;
import com.tsc.clients.dto.ClientDTO;
import com.tsc.clients.dto.ClientUpdateDTO;

import java.util.List;

public interface IClientService  {
    ClientDTO save(ClientCreateDTO entity);
    ClientDTO update(Long id, ClientUpdateDTO entity);
    ClientDTO delete(Long id);
    ClientDTO findById(Long id);
    List<ClientDTO> getAll();
}
