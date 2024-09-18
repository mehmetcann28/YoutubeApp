package com.mehmetc.gui;

import com.mehmetc.entity.Comment;
import com.mehmetc.repository.CommentRepository;

public class CommentGui {
	public static void main(String[] args) {
		Comment comment = new Comment(1L,1L,"yorum");
		CommentRepository commentRepository = new CommentRepository();
		
		commentRepository.save(comment);
	}
}