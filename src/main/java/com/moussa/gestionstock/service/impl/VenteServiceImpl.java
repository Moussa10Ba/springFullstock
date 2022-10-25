package com.moussa.gestionstock.service.impl;

import com.moussa.gestionstock.dto.LigneVenteDto;
import com.moussa.gestionstock.dto.VentesDto;
import com.moussa.gestionstock.exception.EntityNotFoundException;
import com.moussa.gestionstock.exception.ErrorCodes;
import com.moussa.gestionstock.exception.InvalidEntityException;
import com.moussa.gestionstock.model.Article;
import com.moussa.gestionstock.model.LigneVente;
import com.moussa.gestionstock.model.Ventes;
import com.moussa.gestionstock.repository.ArticleRepository;
import com.moussa.gestionstock.repository.LigneVenteRepository;
import com.moussa.gestionstock.repository.VentesRepository;
import com.moussa.gestionstock.service.VenteService;
import com.moussa.gestionstock.validator.VentesValidator;
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
public class VenteServiceImpl implements VenteService {
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public VenteServiceImpl(VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository, ArticleRepository articleRepository) {
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public VentesDto save(VentesDto ventesDto) {
        List<String> errors = VentesValidator.validate(ventesDto);
        if(!errors.isEmpty()){
            log.error("Invalid Vente", ventesDto);
            throw new InvalidEntityException("La vente que vous essayer d'enregistrer contient des erreurs",ErrorCodes.VENTE_NOT_VALID,errors);
        }
            List<String> errorsArticles = new ArrayList<>();
        if (ventesDto.getLigneVentes() != null){
            ventesDto.getLigneVentes().forEach(ligneVente -> {
                Optional<Article> article = articleRepository.findById(ligneVente.getArticle().getId());
                if (article==null || article.isEmpty()){
                    errorsArticles.add("Impossible d'ajouter a une ventes un article null");
                }
            });
        }
        if (!errors.isEmpty()){
            log.error("Article Invalid");
            throw new InvalidEntityException("Article does not exist in DB", ErrorCodes.ARTICLE_NOT_VALID,errorsArticles);
        }
        Ventes savedVente = ventesRepository.save(VentesDto.toEntity(ventesDto));

        if (ventesDto.getLigneVentes() !=null){
            ventesDto.getLigneVentes().forEach(lgVnte -> {
                LigneVente ligneVente= LigneVenteDto.toEntity(lgVnte);
                ligneVente.setVente(savedVente);
            });
        }

        return VentesDto.fromEntity(savedVente);

    }

    @Override
    public VentesDto findById(Integer id) {

        if (id == null){
            log.error("ID vente is null");
            return null;
        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow( ()-> new EntityNotFoundException("Vente with ID"+ id + "does not exist in DB" + ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public VentesDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error("Code Vente is null");
            return null;
        }
        return ventesRepository.findVentesByCode(code).map(VentesDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Ventes with code"+ code +"does not exist in DB" +ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Id vente is null");
            return;
        }
        ventesRepository.deleteById(id);
    }
}
