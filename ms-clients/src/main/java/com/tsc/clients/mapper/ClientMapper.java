package com.tsc.clients.mapper;

import com.tsc.clients.dto.ClientCreateDTO;
import com.tsc.clients.dto.ClientDTO;
import com.tsc.clients.dto.ClientUpdateDTO;
import com.tsc.clients.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public Client clientDTOToClient(ClientDTO client) {
        return Client.builder()
                .id(client.getId())
                .name(client.getName())
                .phone(client.getPhone())
                .age(client.getAge())
                .address(client.getAddress())
                .gender(client.getGender())
                .identificationNumber(client.getIdentificationNumber())
                .status(client.getStatus())
                .build();
    }


    public ClientDTO clientToClientDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .phone(client.getPhone())
                .age(client.getAge())
                .address(client.getAddress())
                .gender(client.getGender())
                .identificationNumber(client.getIdentificationNumber())
                .status(client.getStatus())
                .build();
    }

    public Client clientCreateDTOToClient(ClientCreateDTO client) {
        return Client.builder()
                .name(client.getName())
                .phone(client.getPhone())
                .age(client.getAge())
                .identificationNumber(client.getIdentificationNumber())
                .password(client.getPassword())
                .gender(client.getGender())
                .address(client.getAddress())
                .status(true)
                .build();
    }

    public Client patchClient(Client client,ClientUpdateDTO clientDTO) {
        if(clientDTO.getIdentificationNumber()!=null){
            client.setIdentificationNumber(clientDTO.getIdentificationNumber());
        }
        if(clientDTO.getName()!=null){
            client.setName(clientDTO.getName());
        }
        if(clientDTO.getPhone()!=null){
            client.setPhone(clientDTO.getPhone());
        }
        if(clientDTO.getAge()!=null){
            client.setAge(clientDTO.getAge());
        }
        if(clientDTO.getGender()!=null){
            client.setGender(clientDTO.getGender());
        }
        if(clientDTO.getAddress()!=null){
            client.setAddress(clientDTO.getAddress());
        }
        if(clientDTO.getPassword()!=null){
            client.setPassword(clientDTO.getPassword());
        }
        if(clientDTO.getStatus()!=null){
            client.setStatus(clientDTO.getStatus());
        }
        return client;
    }
}
