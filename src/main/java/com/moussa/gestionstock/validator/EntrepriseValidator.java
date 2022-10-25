package com.moussa.gestionstock.validator;

import com.moussa.gestionstock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto entrepriseDto) {
        List<String> errors = new ArrayList<>();
        if (entrepriseDto == null) {
            errors.add("Le nom est obligatoire");
            errors.add("L'email est obligatoire");
            errors.add("Le CodeFiscal est obligatoire");
            errors.add("Le numero de telephone  est obligatoire");
            errors.add("L'adresse est obligatoire");
        }
        if (!StringUtils.hasLength(entrepriseDto.getNom())) {
            errors.add("Le nom est obligatoire");
        }
        if (!StringUtils.hasLength(entrepriseDto.getCodeFiscal())) {
            errors.add("Le CodeFiscal est obligatoire");
        }
        if (!StringUtils.hasLength(entrepriseDto.getEmail())) {
            errors.add("L'email est obligatoire");
        }
        if (!StringUtils.hasLength(entrepriseDto.getNumTel())) {
            errors.add("Le numero de telephone  est obligatoire");
        }
        if (entrepriseDto.getAdresse() == null) {
            errors.add(" L'adresse est obligatoire");
        } else {
            if (!StringUtils.hasLength(entrepriseDto.getAdresse().getAdresse1())) {
                errors.add("Le champs adresse 1 est obligatoire");
            }
            if (!StringUtils.hasLength(entrepriseDto.getAdresse().getVille())) {
                errors.add("Le champs ville est obligatoire");
            }
            if (!StringUtils.hasLength(entrepriseDto.getAdresse().getCodePostale())) {
                errors.add("Le champs code postal est obligatoire");
            }
            if (!StringUtils.hasLength(entrepriseDto.getAdresse().getPays())) {
                errors.add("Le champs code pays est obligatoire");
            }
        }
        return errors;
    }
}
