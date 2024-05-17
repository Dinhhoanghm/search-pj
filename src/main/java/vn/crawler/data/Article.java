package vn.crawler.data;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class Article {
    private Integer id;
    private String title;
    private String author;
    private String content;
    private String date;
    private String link;


}