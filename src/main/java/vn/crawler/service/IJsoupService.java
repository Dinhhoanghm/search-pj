package vn.crawler.service;

import vn.crawler.data.Article;

import java.io.IOException;
import java.util.List;


public interface IJsoupService {

    public List<Article> crawlData(String url) throws IOException;

    public List<Article> crawlNewData(String url) throws IOException;

    public List<Article> crawlNewData2(String url) throws IOException;


}
