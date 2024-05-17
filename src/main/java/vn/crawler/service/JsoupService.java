package vn.crawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import vn.crawler.data.Article;
import vn.crawler.repo.ArticleRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JsoupService {
    private final ArticleRepository articleRepository;

    public JsoupService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).
                userAgent("Mozilla/5.0")
                .parser(new Parser(new HtmlTreeBuilder()))
                .get();
    }

    public List<Article> crawlData(String url) throws IOException {
        Document document = getDocument(url);
        Elements articles = document.getElementsByClass("ipsDataItem_main");
        List<Article> article = articles.stream()
                .map(s -> {
                    Element titleElement = s.select("a[href]").first();
                    Element authorElement = s.getElementsByClass("ipsDataItem_meta ipsType_reset ipsType_light ipsType_blendLinks")
                            .first()
                            .select("a").first();
                    Element dateElement = s.getElementsByClass("ipsDataItem_meta ipsType_reset ipsType_light ipsType_blendLinks")
                            .first()
                            .select("time").first();
                    Document contentDocument = null;
                    try {
                        contentDocument = getDocument(titleElement.attr("href"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Element contentElement = contentDocument.getElementsByClass("ipsType_normal ipsType_richText ipsPadding_bottom ipsContained")
                            .first();
                    String content = contentElement.select("span").stream().map(span -> span.text())
                            .collect(Collectors.joining());
                    Article articleNew = new Article();
                    articleNew.setTitle(titleElement.text());
                    articleNew.setAuthor(authorElement != null ? authorElement.text() : null);
                    articleNew.setLink(titleElement.attr("href"));
                    articleNew.setContent(content);
                    articleNew.setDate(dateElement.attr("datetime"));
                    articleRepository.insert(articleNew);
                    return articleNew;

                })
                .collect(Collectors.toList());
        return article;
    }

    public List<Article> crawlNewData(String url) throws IOException {
        Document document = getDocument(url);
        Elements articles = document.getElementsByClass("td-module-meta-info");
        List<Article> article = articles.stream()
                .map(s -> {
                    Element titleElement = s.getElementsByClass("entry-title td-module-title").first();
                    Element authorElement = s.getElementsByClass("td-post-author-name")
                            .first();
                    Element dateElement = s.getElementsByClass("td-post-date")
                            .first()
                            .select("time").first();
                    ;
                    Document contentDocument = null;
                    try {
                        contentDocument = getDocument(titleElement.select("a[href]").first().attr("href"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Element contentElement = contentDocument.getElementsByClass("td-post-content tagdiv-type").first();
                    String content = contentElement != null ? contentElement.select("p").stream().map(p -> p.text())
                            .collect(Collectors.joining()) : "";
                    Article articleNew = new Article();
                    articleNew.setTitle(titleElement != null ? titleElement.text(): "");
                    articleNew.setAuthor(authorElement != null ? authorElement.text() : null);
                    articleNew.setLink(titleElement != null ? titleElement.select("a[href]").first().attr("href") :"");
                    articleNew.setContent(content);
                    articleNew.setDate(dateElement != null ? dateElement.text() : "");
                    return articleNew;

                })
                .collect(Collectors.toList());
        return article;
    }

    public List<Article> crawlNewData2(String url) throws IOException {
        Document document = getDocument(url);
        Elements articles = document.getElementsByClass("jeg_postblock_content");
        List<Article> article = articles.stream()
                .map(s -> {
                    Element titleElement = s.getElementsByClass("jeg_post_title").first();
                    Element authorElement = s.getElementsByClass("jeg_meta_author")
                            .first();
                    Element dateElement = s.getElementsByClass("jeg_meta_date")
                            .first()
                    ;
                    Document contentDocument = null;
                    try {
                        contentDocument = getDocument(titleElement.select("a[href]").first().attr("href"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Element contentElement = contentDocument.getElementsByClass("content-inner").first();
                    String content = contentElement != null ? contentElement.select("p").stream().map(p -> p.text())
                            .collect(Collectors.joining()) : "";
                    Article articleNew = new Article();
                    articleNew.setTitle(titleElement != null ? titleElement.text(): "");
                    articleNew.setAuthor(authorElement != null ? authorElement.text() : null);
                    articleNew.setLink(titleElement != null ? titleElement.select("a[href]").first().attr("href") : "");
                    articleNew.setContent(content);
                    articleNew.setDate(dateElement != null ? dateElement.text() :"");
                    return articleNew;

                })
                .collect(Collectors.toList());
        return article;
    }


}
