package com.example.demo.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;

@Service
public interface CommentService {
	

	void addComment(Comment comment);
	void editComment(Comment comment);
	Comment findCommentById(int commentId);
	List<Comment> findAllComments();

}
