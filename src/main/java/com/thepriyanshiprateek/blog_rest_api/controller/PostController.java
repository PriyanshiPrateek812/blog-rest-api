package com.thepriyanshiprateek.blog_rest_api.controller;

import com.thepriyanshiprateek.blog_rest_api.entity.Post;
import com.thepriyanshiprateek.blog_rest_api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        var data = postService.getAllPosts();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id){
        var data = postService.getPostById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post){
        var data = postService.savePost(post);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable("id") Long id){
        var data = postService.updatePost(post, id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
