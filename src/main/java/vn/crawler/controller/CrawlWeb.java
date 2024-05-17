package vn.crawler.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import vn.crawler.data.Article;
import vn.crawler.data.CrawlerRequest;
import vn.crawler.service.IJsoupService;
import vn.crawler.service.JsoupService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static vn.crawler.config.json.Json.encode;

@Service
@Configuration
public class CrawlWeb {
    private final IJsoupService jsoupService;
    private final CrawlerRequest crawlerRequest;

    public CrawlWeb(JsoupService jsoupService, CrawlerRequest crawlerRequest) {
        this.jsoupService = jsoupService;
        this.crawlerRequest = crawlerRequest;
    }
//    @PostConstruct
//    public void crawlerWeb() throws IOException {
//
//        List<Article> articles =jsoupService.crawlData(crawlerRequest.getUrl());
//        for (int i = 1; i < 51 ; i ++ ){
//            List<Article> articleList = jsoupService.crawlData(String.format("%spage/%s/",crawlerRequest.getUrl(),i));
//            articles.addAll(articleList);
//            System.out.println(i);
//        }
//        BufferedWriter writer = new BufferedWriter(new FileWriter(crawlerRequest.getFileName()));
//        System.out.println(articles.size());
//        articles.stream().forEach(s -> {
//
//            try {
//                writer.write(encode(s) + ",");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        try {
//            writer.close();
//        } catch (IOException e) {
//
//            throw new RuntimeException(e);
//        }
//        System.out.println("success");
//    }

    //    @PostConstruct
//    public void crawlerNewWeb() throws IOException {
//
//        List<Article> articles = jsoupService.crawlNewData2(crawlerRequest.getUrl3());
//        for (int i = 2; i < 958; i++) {
//            List<Article> articleList = jsoupService.crawlNewData2(String.format("%s%s%s", "https://www.newsbtc.com/page/", i, "/?s=blockchain"));
//            articles.addAll(articleList);
//            System.out.println(i);
//        }
//        BufferedWriter writer = new BufferedWriter(new FileWriter(crawlerRequest.getFileName()));
//        System.out.println(articles.size());
//        articles.stream().forEach(s -> {
//
//            try {
//                writer.write(encode(s) + ",");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        try {
//            writer.close();
//        } catch (IOException e) {
//
//            throw new RuntimeException(e);
//        }
//        System.out.println("success");
//    }
    @PostConstruct
    public void crawlerNewWeb2() throws IOException {

        List<Article> articles = jsoupService.crawlNewData(crawlerRequest.getUrl2());
        for (int i = 2; i < 649; i++) {
            List<Article> articleList = jsoupService.crawlNewData(String.format("%s%s%s", "https://www.the-blockchain.com/page/", i, "/?s=blockchain"));
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
