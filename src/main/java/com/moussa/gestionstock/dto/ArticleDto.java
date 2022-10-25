package com.moussa.gestionstock.dto;

import com.moussa.gestionstock.model.Article;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ArticleDto {
    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixunitaireht;

    private BigDecimal tauxTva;

    private BigDecimal prixunitaireTtc;

    private String photo;

    private Integer idEntreprise;

    private CategoryDto categorie;

    public static ArticleDto fromEntity(Article article){
        if (article == null){
            return  null;
        }
        return ArticleDto.builder()
                .id(article.getId())
                .designation(article.getDesignation())
                .prixunitaireht(article.getPrixunitaireht())
                .tauxTva(article.getTauxtva())
                .prixunitaireTtc(article.getPrixunitairettc())
                .photo(article.getPhoto())
                .categorie(CategoryDto.fromEntity(article.getCategory()))
                .idEntreprise(article.getIdEntreprise())
                .build();
    }

    public static  Article toEntity(ArticleDto articleDto){
        if (articleDto == null){
            return null;
        }
        Article article = new Article();
        article.setCategory(CategoryDto.toEntity(articleDto.getCategorie()));
        article.setId(articleDto.getId());
        article.setCodeAricle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPhoto(articleDto.getPhoto());
        article.setPrixunitaireht(articleDto.getPrixunitaireht());
        article.setPrixunitairettc(articleDto.getPrixunitaireTtc());
        article.setTauxtva(articleDto.getTauxTva());
        article.setIdEntreprise(articleDto.getIdEntreprise());
        return article;
    }
}
