package com.example.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.blog.models.Post;
import com.example.blog.repositories.PostRepository;

@Service
@Primary
public class PostServiceJpaImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Override
    public List<Post> findAll() {
        return this.postRepo.findAll();
    }

    @Override
    public List<Post> findLatest5() {
        return this.postRepo.findLatest5Posts();
    }

    @Override
    public Post findById(Long id) {
//        return this.postRepo.findOne(id);
    	return null;
    }

    @Override
    public Post create(Post post) {
        return null;
    }

    @Override
    public Post edit(Post post) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
