package com.sample.articleservice.services;

import com.sample.articleservice.model.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getArticles(Long time);

    Article getArticle(Long articleId, Long time);
}
