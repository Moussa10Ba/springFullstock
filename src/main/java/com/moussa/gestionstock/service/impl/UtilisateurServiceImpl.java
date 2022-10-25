package com.moussa.gestionstock.service.impl;

import com.moussa.gestionstock.dto.UtilisateurDto;
import com.moussa.gestionstock.exception.EntityNotFoundException;
import com.moussa.gestionstock.exception.ErrorCodes;
import com.moussa.gestionstock.exception.InvalidEntityException;
import com.moussa.gestionstock.repository.UtilisateurRepository;
import com.moussa.gestionstock.service.UtilisateurService;
import com.moussa.gestionstock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
       List<String> errors = UtilisateurValidator.validate(utilisateurDto);
       if (!errors.isEmpty()){
           log.error("L'utilisateur saisi n'est pas valide",utilisateurDto);
          throw new InvalidEntityException("L'utilisateur saisi n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID,errors);
       }
       return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto)));
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null){
            log.error("Aucun id fourni");
            return null;
        }
        return utilisateurRepository.findById(id).map(UtilisateurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucun Utilisateur n'existe avec l'id " + id + "en BDD", ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Aucun id fourni");
            return;
        }
        utilisateurRepository.deleteById(id);
    }
}
