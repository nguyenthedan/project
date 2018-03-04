package com.example.demo;

//import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

//import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.wallride.domain.Article;
//import org.wallride.domain.BlogLanguage;
//import org.wallride.service.ArticleService;
//import org.wallride.web.controller.guest.article.ArticleSearchForm;
//import org.wallride.web.support.Pagination;

@Controller
@RequestMapping("/")
public class IndexController {
//	@Inject
//	private ArticleService articleService;

	@RequestMapping
	public String index(
			@PageableDefault(10) Pageable pageable,
			Model model,
			HttpServletRequest servletRequest) {

//		Page<Article> articles = articleService.getArticles(form.toArticleSearchRequest(), pageable);
//		model.addAttribute("articles", articles);
//		model.addAttribute("pageable", pageable);
//		model.addAttribute("pagination", new Pagination<>(articles, servletRequest));
		return "index";
	}
}
