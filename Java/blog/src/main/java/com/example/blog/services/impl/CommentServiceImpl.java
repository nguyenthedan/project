package com.example.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.blog.models.Comment;
import com.example.blog.repositories.CommentRepository;
import com.example.blog.services.CommentService;

@Service
@Primary
public class CommentServiceImpl implements CommentService {
	
	@Autowired
    private CommentRepository commentRepo;

	@Override
	public Comment addComment(Comment comment) {
		return commentRepo.save(comment);
	}

}
