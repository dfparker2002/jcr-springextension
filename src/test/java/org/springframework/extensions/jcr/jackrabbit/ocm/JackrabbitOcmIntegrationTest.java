/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.extensions.jcr.jackrabbit.ocm;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.extensions.jcr.jackrabbit.ocm.components.ArticleService;
import org.springframework.extensions.jcr.jackrabbit.ocm.components.NewsService;
import org.springframework.extensions.jcr.jackrabbit.ocm.model.Article;
import org.springframework.extensions.jcr.jackrabbit.ocm.model.News;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.fail;

/**
 * Test Mapper
 *
 * @author <a href="mailto:christophe.lombart@sword-technologies.com">Christophe Lombart</a>
 */
@ContextConfiguration
public class JackrabbitOcmIntegrationTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private NewsService newsService;

    @org.junit.Test
    public void testComponents() {
        try {
            logger.info("Add article");
            Article article = new Article();
            article.setPath("/article");
            article.setAuthor("Christophe");
            article.setContent("This is an interesting content");
            article.setCreationDate(new Date());
            article.setDescription("This is the article description");
            article.setTitle("Article Title");

            articleService.createArticle(article);

            logger.info("Check News");
            for (News newsFound : newsService.getNews()) {
                logger.info("News found : " + newsFound.getContent());
            }
        } catch (Exception e) {
            logger.error("Failed", e);
            fail(e.getMessage());

        }

    }
}