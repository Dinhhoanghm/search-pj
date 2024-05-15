package vn.cuong.crawler.repo;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import vn.cuong.crawler.data.Article;

import static crawler_2fdata.tables.Article.ARTICLE;
@Repository
public class ArticleRepository  {
	private final DSLContext dslContext;

    public ArticleRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
	public void insert(Article article) {
		dslContext.insertInto(ARTICLE)
                .set(ARTICLE.TITLE, article.getTitle())
                .set(ARTICLE.CONTENT, article.getContent())
                .set(ARTICLE.AUTHOR, article.getAuthor())
                .set(ARTICLE.DATE,article.getDate())
				.set(ARTICLE.LINK,article.getLink())
				.execute();

	}
}
