package com.example.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
