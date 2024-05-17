package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {
	@Autowired
	private ArticleService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model, @Param("keyword") String keyword) {
		List<Article> listArticles = service.listAll(keyword);
		model.addAttribute("listArticles", listArticles);
		model.addAttribute("keyword", keyword);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewArticleForm(Model model) {
		Article article = new Article();
		model.addAttribute("article", article);
		
		return "new_article";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveArticle(@ModelAttribute("article") Article article) {
		service.save(article);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditArticleForm(@PathVariable(name = "id") Integer id) {
		ModelAndView mav = new ModelAndView("edit_article");
		
		Article article = service.get(id);
		mav.addObject("article", article);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteArticle(@PathVariable(name = "id") Integer id) {
		service.delete(id);
		
		return "redirect:/";
	}
}
