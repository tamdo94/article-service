package com.sample.articleservice.entities.key;

import java.io.Serializable;

public class ArticleDiscountId implements Serializable {

    private Long articleId;
    private Long discountId;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }
}
