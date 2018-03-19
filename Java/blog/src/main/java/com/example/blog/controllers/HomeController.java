package com.example.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.blog.models.Post;
import com.example.blog.services.PostService;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Post> listPosts = postService.findAll();
        model.addAttribute("listPosts", listPosts);

        return "index";
    }

}