package com.sample.articleservice.services.impl;

import com.sample.articleservice.dao.ArticleDiscountRepository;
import com.sample.articleservice.dao.ArticleRepository;
import com.sample.articleservice.dao.DiscountRepository;
import com.sample.articleservice.entities.ArticleDiscountEntity;
import com.sample.articleservice.entities.ArticleEntity;
import com.sample.articleservice.entities.DiscountEntity;
import com.sample.articleservice.helper.PriceHelper;
import com.sample.articleservice.model.Article;
import com.sample.articleservice.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private ArticleDiscountRepository articleDiscountRepository;

    public List<Article> getArticles(Long time) {
        var searchTime = time == null ? new Date() : new Date(time);

        var articleModels = new ArrayList<Article>();
        var articles = articleRepository.findAll();
        for (var article: articles) {
            articleModels.add(getArticleInfo(article, searchTime));
        }

        return articleModels;
    }

    public Article getArticle(Long articleId, Long time) {
        var searchTime = time == null ? new Date() : new Date(time);

        var article = articleRepository.findById(articleId);

        if (article.isEmpty()) return null;
        return getArticleInfo(article.get(), searchTime);
    }

    private Article getArticleInfo(ArticleEntity article, Date time) {
        var model = new Article();
        model.setArticleId(article.getId());
        model.setName(article.getName());
        model.setSlogan(article.getSlogan());
        model.setVat(article.getVat());
        model.setPrice(article.getSalesPrice());

        var discountIds = articleDiscountRepository.findByArticleId(article.getId())
                .stream().map(ArticleDiscountEntity::getDiscountId)
                .collect(Collectors.toList());

        var discount = discountRepository.findAllById(discountIds)
                .stream().filter(v -> isValidDiscount(v, time))
                .sorted(Comparator.comparingLong(DiscountEntity::getId)).findFirst();

        if (discount.isPresent()) {
            model.setDiscountId(discount.get().getId());
            model.setDiscountName(discount.get().getName());

            var discountPrice = PriceHelper.priceAfterDiscount(
                    article.getSalesPrice(), discount.get().getPercentage());

            //TODO: what happen if discount price < net price? assume use net price
            if (article.getNetPrice().compareTo(discountPrice) > 0) {
                model.setDiscountPrice(article.getNetPrice());
            } else {
                model.setDiscountPrice(discountPrice);
            }
        }
        return model;
    }

    private boolean isValidDiscount(DiscountEntity discount, Date time) {
        return discount.getApplied() && time.after(discount.getStart()) && time.before(discount.getEnd());
    }
}
