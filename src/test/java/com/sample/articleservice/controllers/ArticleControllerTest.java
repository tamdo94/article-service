package com.sample.articleservice.controllers;

import com.sample.articleservice.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void articles_100_discount_5_percent() throws Exception {
        var responseEntity = template
                .getForEntity("/v1/article?time=1638147600000",
                        Article[].class); //Mon, 29 Nov 2021 1:00:00 GMT

        Article[] articles = responseEntity.getBody();
        assertThat(articles).hasSize(2);
        assertThat(articles[0].getDiscountPrice()).isEqualTo("23.75");
        assertThat(articles[1].getDiscountPrice()).isEqualTo("34.20");
    }

    @Test
    public void articles_100_discount_7_percent() throws Exception {
        var responseEntity = template
                .getForEntity("/v1/article?time=1638190800000",
                        Article[].class); //Mon, 29 Nov 2021 13:00:00 GMT

        Article[] articles = responseEntity.getBody();
        assertThat(articles).hasSize(2);
        assertThat(articles[0].getDiscountPrice()).isEqualTo("23.25");
        assertThat(articles[1].getDiscountPrice()).isEqualTo("34.20");
    }

    @Test
    public void article_100_discount_5_percent() throws Exception {
        var responseEntity = template
                .getForEntity("/v1/article/100?time=1638147600000",
                        Article.class); //Mon, 29 Nov 2021 1:00:00 GMT

        var article = responseEntity.getBody();
        assertThat(article).isNotNull();
        assertThat(article.getDiscountPrice()).isEqualTo("23.75");
    }

    @Test
    public void article_100_discount_7_percent() throws Exception {
        var responseEntity = template
                .getForEntity("/v1/article/100?time=1638190800000",
                        Article.class); //Mon, 29 Nov 2021 13:00:00 GMT

        var article = responseEntity.getBody();
        assertThat(article).isNotNull();
        assertThat(article.getDiscountPrice()).isEqualTo("23.25");
    }
}
