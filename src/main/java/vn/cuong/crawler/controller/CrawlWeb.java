package vn.cuong.crawler.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import vn.cuong.crawler.data.Article;
import vn.cuong.crawler.data.CrawlerRequest;
import vn.cuong.crawler.service.JsoupConfig;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static vn.cuong.crawler.json.Json.encode;

@Service
@Configuration
public class CrawlWeb {
    private final JsoupConfig jsoupConfig;
    private final CrawlerRequest crawlerRequest;

    public CrawlWeb(JsoupConfig jsoupConfig, CrawlerRequest crawlerRequest) {
        this.jsoupConfig = jsoupConfig;
        this.crawlerRequest = crawlerRequest;
    }
    @PostConstruct
    public void crawlerWeb() throws IOException {

        List<Article> articles =jsoupConfig.crawlData(crawlerRequest.getUrl());
        for (int i = 1; i < 44 ; i ++ ){
            List<Article> articleList = jsoupConfig.crawlData(String.format("%spage/%s/",crawlerRequest.getUrl(),i));
            articles.addAll(articleList);
            System.out.println(i);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(crawlerRequest.getFileName()));
        System.out.println(articles.size());
        articles.stream().forEach(s -> {

            try {
                writer.write(encode(s) + ",");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            writer.close();
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
        System.out.println("success");
    }
}
