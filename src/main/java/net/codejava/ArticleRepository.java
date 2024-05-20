package net.codejava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

	@Query("SELECT p FROM Article p WHERE p.title LIKE %?1% OR p.author LIKE %?1% OR p.date LIKE %?1%  OR p.content LIKE %?1% OR p.link LIKE %?1%")
	public List<Article> search(String keyword);
}
