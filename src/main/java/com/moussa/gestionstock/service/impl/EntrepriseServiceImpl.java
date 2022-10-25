package com.moussa.gestionstock.service.impl;

import com.moussa.gestionstock.dto.EntrepriseDto;
import com.moussa.gestionstock.exception.EntityNotFoundException;
import com.moussa.gestionstock.exception.ErrorCodes;
import com.moussa.gestionstock.exception.InvalidEntityException;
import com.moussa.gestionstock.repository.EntrepriseRepository;
import com.moussa.gestionstock.service.EntrepriseService;
import com.moussa.gestionstock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {
    private EntrepriseRepository entrepriseRepository;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);
        if (!errors.isEmpty()){
            log.error("L'entreprise saisie n'est pas valid");
            throw new InvalidEntityException("L'entreprise saisie n'est pas valid", ErrorCodes.ENTREPRISE_NOT_VALID,errors);
        }
        return EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto)));
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null){
            log.error("Entreprise Id cannot be null");
            return null;
        }
        return entrepriseRepository.findById(id).map(EntrepriseDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune entreprise avec l'id " + id + "n'a ete trouve en BDD", ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("L'id est null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
