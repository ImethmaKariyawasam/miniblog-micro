package com.miniblog.comment_service.service;

import com.miniblog.comment_service.client.PostServiceClient;
import com.miniblog.comment_service.model.Comment;
import com.miniblog.comment_service.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostServiceClient postServiceClient;

    public CommentService(CommentRepository commentRepository, PostServiceClient postServiceClient) {
        this.commentRepository = commentRepository;
        this.postServiceClient = postServiceClient;
    }

    public Comment createComment(Long postId, String content, String author) {
        if (!postServiceClient.postExists(postId)) {
            throw new PostNotFoundException(postId);
        }

        Comment comment = new Comment(postId, content, author);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsForPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
