package com.moussa.gestionstock.service.impl;

import com.moussa.gestionstock.dto.FournisseurDto;
import com.moussa.gestionstock.exception.EntityNotFoundException;
import com.moussa.gestionstock.exception.ErrorCodes;
import com.moussa.gestionstock.exception.InvalidEntityException;
import com.moussa.gestionstock.repository.FournisseurRepository;
import com.moussa.gestionstock.service.FournisseurService;
import com.moussa.gestionstock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository){
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        List<String> errors = FournisseurValidator.validate(fournisseurDto);
        if (!errors.isEmpty()){
            log.error("Le fournisseur n'est pas valide");
            throw new InvalidEntityException("Le fournisseur saisi n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto)));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (id == null){
            log.error("Aucun id fourni ");
            return null;
        }
        return fournisseurRepository.findById(id)
                .map(FournisseurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune founisseur avec l'id" + id + "n'a ete trouvee en BDD",ErrorCodes.FOURNISSEUR_NOT_FOUND));
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Aucun id fourni");
            return;
        }
        fournisseurRepository.deleteById(id);
    }
}
