package com.sample.articleservice.dao;

import com.sample.articleservice.entities.ArticleDiscountEntity;
import com.sample.articleservice.entities.key.ArticleDiscountId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleDiscountRepository extends JpaRepository<ArticleDiscountEntity, ArticleDiscountId> {

    List<ArticleDiscountEntity> findByArticleId(Long articleId);
}
