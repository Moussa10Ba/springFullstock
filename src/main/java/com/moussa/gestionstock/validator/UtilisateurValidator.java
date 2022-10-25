package com.moussa.gestionstock.validator;

import com.moussa.gestionstock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto){

        List<String> errors = new ArrayList<>();

        if (utilisateurDto == null){
            errors.add("Veuillez renseigner le nom");
            errors.add("Veuillez renseigner le prenom");
            errors.add("Veuillez renseigner l'email");
            errors.add("Veuillez renseigner le mot de passe");
            errors.add("Veuillez renseigner le l'adresse de l'utilisateur");
            return errors;
        }

        if (!StringUtils.hasLength(utilisateurDto.getNom())){
            errors.add("Veuillez renseigner le nom");
        }
        if (utilisateurDto.getDateNaissance() == null){
            errors.add("Veuillez renseigner la date de Naissance de l'utilisateur");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPrenom())){
            errors.add("Veuillez renseigner le prenom");
        }
        if (!StringUtils.hasLength(utilisateurDto.getEmail())){
            errors.add("Veuillez renseigner l'email");
        }
        if (!StringUtils.hasLength(utilisateurDto.getPassword())){
            errors.add("Veuillez renseigner le mot de passe");
        }
        if (utilisateurDto.getAdresse() == null){
            errors.add("Veuillez renseigner le l'adresse de l'utilisateur");
        }else {
            if ( !StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())){
                errors.add("Le champs adresse 1 est obligatoire");
            }
            if ( !StringUtils.hasLength(utilisateurDto.getAdresse().getVille())){
                errors.add("Le champs ville est obligatoire");
            }
            if ( !StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())){
                errors.add("Le champs code postal est obligatoire");
            }
            if ( !StringUtils.hasLength(utilisateurDto.getAdresse().getPays())){
                errors.add("Le champs code pays est obligatoire");
            }
        }


        return errors;

    }
}
