package com.tsc.clients.serviceimp;

import com.tsc.clients.dto.ClientCreateDTO;
import com.tsc.clients.dto.ClientDTO;
import com.tsc.clients.dto.ClientUpdateDTO;
import com.tsc.clients.mapper.ClientMapper;
import com.tsc.clients.model.Client;
import com.tsc.clients.repository.IClientRepository;
import com.tsc.clients.repository.IPersonRepository;
import com.tsc.clients.service.IClientService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClientService {
    private final static Logger logger = LoggerFactory.getLogger(ClientService.class.getName());
    private final IClientRepository clientRepository;
    private final IPersonRepository personRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(IClientRepository clientRepository, IPersonRepository personRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.personRepository = personRepository;
        this.clientMapper = clientMapper;

    }

    @Override
    public ClientDTO save(ClientCreateDTO entity) {
        Optional<Client> optionalAccount = this.clientRepository.findByIdentificationNumber(entity.getIdentificationNumber());
        if (optionalAccount.isPresent()) {
            logger.error("Client identification number already exists: "+entity.getIdentificationNumber());
            throw new EntityExistsException("Client identification number already exists: "+entity.getIdentificationNumber());
        }
        Client client=clientMapper.clientCreateDTOToClient(entity);
        client.setStatus(true);
        return clientMapper.clientToClientDTO(this.clientRepository.save(client));
    }

    @Override
    public ClientDTO update(Long id, ClientUpdateDTO entity) {
        ClientDTO clientDTO=this.findById(id);
        Client client=clientMapper.clientDTOToClient(clientDTO);
        Client patchedAccount=clientMapper.patchClient(client,entity);
        return clientMapper.clientToClientDTO(this.clientRepository.save(patchedAccount));
    }

    @Override
    public ClientDTO delete(Long id) {
        ClientDTO client=this.findById(id);
        clientRepository.deleteById(id);
        personRepository.deleteById(id);
        return client;
    }

    @Override
    public ClientDTO findById(Long id) {
        Optional<Client> client = this.clientRepository.findById(id);
        if(client.isEmpty()){
            throw new EntityNotFoundException("Client not found");
        }
        return clientMapper.clientToClientDTO(client.get());
    }

    @Override
    public List<ClientDTO> getAll() {
        return this.clientRepository.findAll().stream().map(clientMapper::clientToClientDTO).collect(Collectors.toList());
    }
}
