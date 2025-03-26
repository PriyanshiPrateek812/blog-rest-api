package com.thepriyanshiprateek.blog_rest_api.service.impl;

import com.thepriyanshiprateek.blog_rest_api.entity.Post;
import com.thepriyanshiprateek.blog_rest_api.exception.ResourceNotFoundException;
import com.thepriyanshiprateek.blog_rest_api.repository.PostRepository;
import com.thepriyanshiprateek.blog_rest_api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
//        List<Post> posts = postRepository.findAll();
//        System.out.println("Fetched posts count: " + posts.size());
//        return posts;
    }

    @Override
    public Post getPostById(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        return post;
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public Post updatePost(Post post, Long id) {
        Post savePost = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        savePost.setTitle(post.getTitle());
        savePost.setDescription(post.getDescription());
        savePost.setContent(post.getContent());

        return postRepository.save(savePost);
    }
}
