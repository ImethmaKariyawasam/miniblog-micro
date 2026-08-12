package com.miniblog.post_service.repository;

import com.miniblog.post_service.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
