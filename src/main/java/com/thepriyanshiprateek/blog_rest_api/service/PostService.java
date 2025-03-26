package com.thepriyanshiprateek.blog_rest_api.service;

import com.thepriyanshiprateek.blog_rest_api.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    Post savePost(Post post);
    void deletePost(Long id);
    Post updatePost(Post post, Long id);
}
