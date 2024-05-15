package vn.cuong.crawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.cuong.crawler.data.Article;
import vn.cuong.crawler.repo.ArticleJpaRepository;
import vn.cuong.crawler.repo.ArticleRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JsoupConfig {
    private final ArticleRepository articleRepository;

    public JsoupConfig(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Document getDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36")
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

}
