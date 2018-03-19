package com.example.blog.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.blog.models.Comment;
import com.example.blog.models.Post;
import com.example.blog.models.User;
import com.example.blog.services.CommentService;
import com.example.blog.services.PostService;
import com.example.blog.services.UserService;

@Controller
public class PostController {

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = { "/posts/create" }, method = RequestMethod.GET)
	public ModelAndView getForm() {
		ModelAndView modelAndView = new ModelAndView();
		Post post = new Post();
		modelAndView.addObject("post", post);
		modelAndView.setViewName("posts/create");
		return modelAndView;
	}

	@RequestMapping(value = { "/posts/create" }, method = RequestMethod.POST)
	public String createPost(@Valid Post post) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		post.setAuthor(user);
		postService.create(post);
		return "index";
	}

	@RequestMapping("/posts/view/{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Post post = postService.findById(id);
		Comment comment = new Comment();
		model.addAttribute("post", post);
		model.addAttribute("comment", comment);
		return "/posts/index";
	}

	@RequestMapping(value = { "/posts/{id}/addComment" }, method = RequestMethod.POST)
	public String addComment(@PathVariable("id") Long id, Comment comment) {
		Post post = postService.findById(id);
		comment.setPost(post);
		commentService.addComment(comment);
		return "index";
	}
}
