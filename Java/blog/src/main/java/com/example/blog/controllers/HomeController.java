package com.example.blog.controllers;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.blog.models.Post;
import com.example.blog.services.NotificationService;
import com.example.blog.services.PostService;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Post> latest5Posts = postService.findLatest5();
        model.addAttribute("latest5posts", latest5Posts);

        List<Post> latest3Posts = latest5Posts.stream()
                .limit(3).collect(toList());
        model.addAttribute("latest3posts", latest3Posts);

        return "index";
    }

    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id,
                       Model model) {
        Post post = postService.findById(id);

        if (post == null) {
            notificationService.addErrorMessage(
                    "Cannot find post: " + id);
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "/posts/index";
    }

}