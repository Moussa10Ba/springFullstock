package com.moussa.gestionstock.service.impl;

import com.moussa.gestionstock.dto.ClientDto;
import com.moussa.gestionstock.exception.EntityNotFoundException;
import com.moussa.gestionstock.exception.ErrorCodes;
import com.moussa.gestionstock.exception.InvalidEntityException;
import com.moussa.gestionstock.repository.ClientRepository;
import com.moussa.gestionstock.service.ClientService;
import com.moussa.gestionstock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors  = ClientValidator.validate(clientDto);
        if (!errors.isEmpty()){
            log.error("Client Invalid");
            throw new InvalidEntityException("Le client est invalid", ErrorCodes.CLIENT_NOT_VALID,errors);
        }
        return ClientDto.fromEntity(clientRepository.save(clientDto.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id == null){
            log.error("Id is null");
            return null;
        }
       return clientRepository.findById(id).map(ClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun client avec l'id " + id + " n'existe dans la BDD",ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("L'id est null ");
            return;
        }
        clientRepository.deleteById(id);
    }
}
