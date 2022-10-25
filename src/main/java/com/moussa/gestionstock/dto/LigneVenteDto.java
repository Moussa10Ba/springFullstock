package com.moussa.gestionstock.dto;

import com.moussa.gestionstock.model.Article;
import com.moussa.gestionstock.model.LigneVente;
import com.moussa.gestionstock.model.Ventes;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Integer id;

    private VentesDto vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private Integer idEntreprise;

    private ArticleDto article;

    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .quantite(ligneVente.getQuantite())
                .idEntreprise(ligneVente.getIdEntreprise())
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto){
        if (ligneVenteDto==null){
            return null;
        }
        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setArticle(ArticleDto.toEntity(ligneVenteDto.getArticle()));
        return ligneVente;
    }
}
