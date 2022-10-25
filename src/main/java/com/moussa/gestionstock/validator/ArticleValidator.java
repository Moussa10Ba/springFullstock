package com.moussa.gestionstock.validator;

import com.moussa.gestionstock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto articleDto){
        List<String> errors = new ArrayList<>();

        if (articleDto == null){
            errors.add("Veuillez renseigner le code de l'article");
            errors.add("Veuillez renseigner la designation de l'article");
            errors.add("Veuillez renseigner le prix Unitaire HT de l'article");
            errors.add("Veuillez renseigner le taux TVA de l'article");
            errors.add("Veuillez renseigner le prix Uni TVA de l'article");
            errors.add("Veuillez renseigner une categorie");
            return errors;
        }
            if (!StringUtils.hasLength(articleDto.getCodeArticle())){
                errors.add("Veuillez renseigner le code de l'article");
            }
            if (!StringUtils.hasLength(articleDto.getDesignation())){
                errors.add("Veuillez renseigner la designation de l'article");
            }
            if (articleDto.getPrixunitaireht() == null){
                errors.add("Veuillez renseigner le prix Unitaire HT de l'article");
            }
            if (articleDto.getTauxTva() == null){
            errors.add("Veuillez renseigner le taux TVA de l'article");
             }
             if (articleDto.getPrixunitaireTtc() == null){
            errors.add("Veuillez renseigner le prix Uni TVA de l'article");
             }
             if (articleDto.getCategorie() == null){
            errors.add("Veuillez renseigner une categorie");
             }
             return errors;
    }
}
