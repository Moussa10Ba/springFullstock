package com.moussa.gestionstock.repository;

import com.moussa.gestionstock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Optional<Article> findArticleByCodeAricle(String codeArticle);

}
