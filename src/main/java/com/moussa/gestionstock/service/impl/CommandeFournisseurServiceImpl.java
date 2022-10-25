package com.moussa.gestionstock.service.impl;

import com.moussa.gestionstock.dto.ArticleDto;
import com.moussa.gestionstock.dto.CommandeFournisseurDto;
import com.moussa.gestionstock.dto.LigneCommandeFournisseurDto;
import com.moussa.gestionstock.exception.EntityNotFoundException;
import com.moussa.gestionstock.exception.ErrorCodes;
import com.moussa.gestionstock.exception.InvalidEntityException;
import com.moussa.gestionstock.model.Article;
import com.moussa.gestionstock.model.CommandeFournisseur;
import com.moussa.gestionstock.model.Fournisseur;
import com.moussa.gestionstock.model.LigneCommandeFournisseur;
import com.moussa.gestionstock.repository.*;
import com.moussa.gestionstock.service.CommandeFournisseurService;
import com.moussa.gestionstock.validator.ArticleValidator;
import com.moussa.gestionstock.validator.CommandeClientValidator;
import com.moussa.gestionstock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;

    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository, ArticleRepository articleRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository, FournisseurRepository fournisseurRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) {
        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);


        //Check if CommandeFournisseur is not null
        //Check if CommadeFournisseur have require fields
        //Checf if CommandeFournisseur Have an existing Fournisseur in DB
        // Check if CommandeFournisseur have in his getLigneCommandeFournisseurs valid article

       if (commandeFournisseurDto == null){
               log.error("Des champs sont ivalides", errors);
               throw new InvalidEntityException("Commande fournisseur invalide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID,errors);
       }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(commandeFournisseurDto.getFournisseur().getId());
        if (fournisseur.isEmpty()){
            log.error("Il faut un Fournisseur pour cette commande");
            throw new EntityNotFoundException("Un fournisseur est requis pour creer une coomande fournisseur",ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
                List<String> articleErrors = new ArrayList<>();
       if (commandeFournisseurDto.getLigneCommandeFournisseurs() !=null){
           commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(lgnCmdFour -> {
               Optional<Article> article = articleRepository.findById(lgnCmdFour.getArticle().getId());
               if (article.isEmpty()){
                   articleErrors.add("L'article avec l'id " + lgnCmdFour.getArticle().getId() + "n'existe pas");
               }else{
                   articleErrors.add("Impossible d'ajouter a une commande un article null");
               }

           });
       }
        CommandeFournisseur savedCommandeFournisseur = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));
       if (commandeFournisseurDto.getLigneCommandeFournisseurs()!=null){
           commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(lignCmdFour -> {
               LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(lignCmdFour);
               ligneCommandeFournisseur.setCommandeFournisseur(savedCommandeFournisseur);
               ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
           });
       }

        return CommandeFournisseurDto.fromEntitiy(savedCommandeFournisseur);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        if (id==null){
            log.error("L'Id de la commande est null");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntitiy)
               .orElseThrow(() -> new EntityNotFoundException("Commande Fournisseur with id" + id + "does not exist in DB", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));

    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Code commande fournisseur is null");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntitiy)
                .orElseThrow(() -> new EntityNotFoundException("Commande Fournisseur with code" + code + "does not exist in DB", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND));

    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntitiy)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id==null){
            log.error("Id is null");
            return;
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
