package com.example.blog.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.blog.models.Post;
import com.example.blog.models.User;
import com.example.blog.services.PostService;
import com.example.blog.services.UserService;

@Controller
public class PostController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value={"/posts/create"}, method = RequestMethod.GET)
    public ModelAndView getForm() {
		ModelAndView modelAndView = new ModelAndView();
		Post post = new Post();
		modelAndView.addObject("post", post);
		modelAndView.setViewName("posts/create");
		return modelAndView;
    }
	
	@RequestMapping(value={"/posts/create"}, method = RequestMethod.POST)
    public String createPost(@Valid Post post) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		post.setAuthor(user);
		postService.create(post);
        return "index";
    }
}
