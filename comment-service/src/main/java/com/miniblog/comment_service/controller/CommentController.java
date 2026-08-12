package com.miniblog.comment_service.controller;

import com.miniblog.comment_service.dto.CommentRequest;
import com.miniblog.comment_service.model.Comment;
import com.miniblog.comment_service.service.CommentService;
import com.miniblog.comment_service.service.PostNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(
            @RequestBody CommentRequest request,
            Authentication authentication) {
        try {
            Comment comment = commentService.createComment(
                    request.getPostId(), request.getContent(), authentication.getName());
            return ResponseEntity.ok(comment);
        } catch (PostNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getCommentsForPost(@PathVariable Long postId) {
        return commentService.getCommentsForPost(postId);
    }
}
