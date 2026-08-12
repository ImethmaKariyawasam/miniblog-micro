package com.miniblog.comment_service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Component
public class PostServiceClient {

    private final RestClient restClient;

    public PostServiceClient(@Value("${post-service.base-url}") String postServiceBaseUrl) {
        this.restClient = RestClient.create(postServiceBaseUrl);
    }

    public boolean postExists(Long postId) {
        try {
            restClient.get()
                    .uri("/posts/{id}", postId)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }
}
