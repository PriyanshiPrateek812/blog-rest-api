package com.thepriyanshiprateek.blog_rest_api.controller;

import com.thepriyanshiprateek.blog_rest_api.entity.Comment;
import com.thepriyanshiprateek.blog_rest_api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RequestMapping("/api/posts")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable("postId") Long postId,
                                                 @RequestBody Comment comment){
        var data = commentService.createComment(postId, comment);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable("postId") Long postId){
        var data = commentService.getCommentByPostId(postId);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("postId") Long postId,
                                                  @PathVariable("commentId") Long commentId){
        var data = commentService.getCommentById(postId, commentId);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("postId") Long postId,
                                                 @PathVariable("commentId") Long commentId,
                                                 @RequestBody Comment comment){
        var data = commentService.updateComment(postId, commentId, comment);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("postId") Long postId,
                                              @PathVariable("commentId") Long commentId){
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.noContent().build();
    }
}
