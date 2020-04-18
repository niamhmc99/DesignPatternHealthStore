package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CommentRepo;
import com.example.demo.interfaces.CommentService;
import com.example.demo.model.Comment;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentRepo commentRepo;

	@Override
	public void addComment(Comment comment) {
		commentRepo.save(comment);		
	}

	@Override
	public void editComment(Comment comment) {
		
	}

	@Override
	public Comment findCommentById(int commentId) {
		// TODO Auto-generated method stub
		return commentRepo.findById(commentId).orElse(null);
	}

	@Override
	public List<Comment> findAllComments() {
		// connects to the database and runs a query
		List<Comment> comments = new ArrayList<>();
		// adds each User into the array
		commentRepo.findAll().forEach(comments::add);
		return comments;
	}

}
