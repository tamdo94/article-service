package com.sample.articleservice.controllers;

import com.sample.articleservice.exceptions.NotFoundException;
import com.sample.articleservice.model.Article;
import com.sample.articleservice.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> getArticles(@RequestParam(required = false) Long time) {
        var articles = articleService.getArticles(time);
        articles.forEach(art -> {
            var self = linkTo(methodOn(this.getClass()).getArticle(art.getArticleId(), time));
            art.add(self.withRel("detail"));
        });

        return articles;
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable Long id, @RequestParam(required = false) Long time) {
        var article = articleService.getArticle(id, time);

        if (article == null) throw new NotFoundException("Article not found.");

        var linkToUsers = linkTo(methodOn(this.getClass()).getArticles(time));
        var self = linkTo(methodOn(this.getClass()).getArticle(id, time));

        article.add(self.withSelfRel());
        article.add(linkToUsers.withRel("all-articles"));
        return article;
    }

}
