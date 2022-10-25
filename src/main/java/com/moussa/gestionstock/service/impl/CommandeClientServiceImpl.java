package com.moussa.gestionstock.service.impl;

import com.moussa.gestionstock.dto.CommandeClientDto;
import com.moussa.gestionstock.dto.LigneCommandeClientDto;
import com.moussa.gestionstock.exception.EntityNotFoundException;
import com.moussa.gestionstock.exception.ErrorCodes;
import com.moussa.gestionstock.exception.InvalidEntityException;
import com.moussa.gestionstock.model.Article;
import com.moussa.gestionstock.model.Client;
import com.moussa.gestionstock.model.CommandeClient;
import com.moussa.gestionstock.model.LigneCommandeClient;
import com.moussa.gestionstock.repository.ArticleRepository;
import com.moussa.gestionstock.repository.ClientRepository;
import com.moussa.gestionstock.repository.CommandeClientRepository;
import com.moussa.gestionstock.repository.LigneCommandeClientRepository;
import com.moussa.gestionstock.service.CommandeClientService;
import com.moussa.gestionstock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository,
                                     ArticleRepository articleRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository =ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if (!errors.isEmpty()){
            log.error("Invalid Commande Client");
            throw new InvalidEntityException("Invalid Commande Client", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
        }
        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());
        if (client.isEmpty()){
            log.warn("Client with Id {} was not found in the DB",commandeClientDto.getClient().getId());
            throw  new EntityNotFoundException("Aucune client avec l'id" + commandeClientDto.getClient().getId() + "n'a ete trouver",ErrorCodes.CLIENT_NOT_FOUND);
        }
        List<String> articleErrors = new ArrayList<>();
        if (commandeClientDto.getLigneCommandeClients() != null){
            commandeClientDto.getLigneCommandeClients().forEach(lignCmdClient-> {
                Optional<Article> article = articleRepository.findById(lignCmdClient.getArticle().getId());
                if (article.isEmpty()){
                    articleErrors.add("L'article avec l'id " + lignCmdClient.getArticle().getId() + "n'existe pas");
                }else{
                    articleErrors.add("Impossible d'ajouter a une commande un article null");
                }
            });
        }
        if (!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BD ",ErrorCodes.ARTICLE_NOT_FOUND,articleErrors);
        }
        CommandeClient savedCommandeClient = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));
       if (commandeClientDto.getLigneCommandeClients() !=null){
           commandeClientDto.getLigneCommandeClients().forEach(lignCmdClient -> {
               LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(lignCmdClient);
               ligneCommandeClient.setCommandeClient(savedCommandeClient);
               ligneCommandeClientRepository.save(ligneCommandeClient);
           });
       }
        return CommandeClientDto.fromEntitiy(savedCommandeClient);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if(id==null){
            log.error("Id IS NULL");
            return null;
        }
    return     commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntitiy)
                .orElseThrow(
                        ()-> new EntityNotFoundException("Aucune commande avec l'id " + id + "n'existe en BD",ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));

    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Code commande client is required");
            return null;
        }
        return     commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntitiy)
                .orElseThrow(
                        ()-> new EntityNotFoundException("Aucune commande avec le code" + code + "n'existe en BD",ErrorCodes.COMMANDE_CLIENT_NOT_FOUND));

    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntitiy)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Id commande client is null");
            return;
        }
        commandeClientRepository.deleteById(id);
    }
}
