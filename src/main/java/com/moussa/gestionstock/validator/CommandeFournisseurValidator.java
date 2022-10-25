package com.moussa.gestionstock.validator;

import com.moussa.gestionstock.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {

    public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto){
        List<String> errors = new ArrayList<>();
        if (commandeFournisseurDto == null){
            errors.add("Le Fournisseur de la commande est requis");
            errors.add("Les lignes de commandes fournisseur sont requises");
            errors.add("Article requis pour chaque ligne de commande Fournisseur");
            return errors;
        }
        if(commandeFournisseurDto.getFournisseur() == null){
            errors.add("Le Fournisseur de la commande est requis");
        }
        if(commandeFournisseurDto.getLigneCommandeFournisseurs() == null){
            errors.add("Les lignes de commandes fournisseur sont requises");
        }else {
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach( ligneCommandeFournisseurDto -> {
                if (ligneCommandeFournisseurDto.getArticle() == null){
                    errors.add("Article requis pour chaque ligne de commande Fournisseur");
                }
            });
        }
        return errors;
    }
}
