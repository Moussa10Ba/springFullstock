package com.moussa.gestionstock.service.impl;

import com.moussa.gestionstock.dto.ArticleDto;
import com.moussa.gestionstock.exception.EntityNotFoundException;
import com.moussa.gestionstock.exception.ErrorCodes;
import com.moussa.gestionstock.exception.InvalidEntityException;
import com.moussa.gestionstock.model.Article;
import com.moussa.gestionstock.repository.ArticleRepository;
import com.moussa.gestionstock.service.ArticleService;
import com.moussa.gestionstock.validator.ArticleValidator;
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
public class AricleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository ;

    @Autowired
    public AricleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if (!errors.isEmpty()){
            log.error("Article is not valid",articleDto );
            throw new InvalidEntityException("L'article n'est pas valid", ErrorCodes.ARTICLE_NOT_VALID,errors);
        }
        Article savedArticle = articleRepository.save(ArticleDto.toEntity(articleDto));
        return  ArticleDto.fromEntity(savedArticle);
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id == null){
            log.error("Entity not found");
            return null;
        }
        Optional<Article> article = articleRepository.findById(id);
        ArticleDto dto = ArticleDto.fromEntity(article.get());
        return Optional.of(dto).orElseThrow(()->
                new EntityNotFoundException
                        ("Aucun article avec l'id = " + id + "n'a ete trouve", ErrorCodes.ARTICLE_NOT_FOUND)
        );
//
//        return Optional.of( ArticleDto.fromEntity(article.get())).orElseThrow(()->
//                new EntityNotFoundException
//                        ("Aucun article avec l'id = " + id + "n'a ete trouve", ErrorCodes.ARTICLE_NOT_FOUND)
//        );
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        if (!StringUtils.hasLength(codeArticle)){
            log.error("Article code is null");
            return null;
        }
        Optional<Article> article = articleRepository.findArticleByCodeAricle(codeArticle);
        ArticleDto dto = ArticleDto.fromEntity(article.get());
        return Optional.of(dto).orElseThrow(()->
                new EntityNotFoundException
                        ("Aucun article avec l'id = " + codeArticle + "n'a ete trouve", ErrorCodes.ARTICLE_NOT_FOUND)
        );

    }

    @Override
    public List<ArticleDto> findAll() {
       return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Cette article n'existe pas");
            return;
        }
        articleRepository.deleteById(id);
    }
}
