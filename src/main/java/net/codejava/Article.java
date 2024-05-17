package net.codejava;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Article {
	private Integer id;
	private String title;
	private String author;
	private String content;
	private String date;
    private String link;
	protected Article() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	 public String getLink() {
		return link;
	 }
	 public void setLink(String link) {
		this.link = link;
	 }

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", author=" + author + ", content=" + content + ", date=" + date +", link=" + link
				+ "]";
	}	
}
