package com.thepriyanshiprateek.blog_rest_api.service.impl;

import com.thepriyanshiprateek.blog_rest_api.entity.Comment;
import com.thepriyanshiprateek.blog_rest_api.entity.Post;
import com.thepriyanshiprateek.blog_rest_api.exception.BlogApiException;
import com.thepriyanshiprateek.blog_rest_api.exception.ResourceNotFoundException;
import com.thepriyanshiprateek.blog_rest_api.repository.CommentRepository;
import com.thepriyanshiprateek.blog_rest_api.repository.PostRepository;

import com.thepriyanshiprateek.blog_rest_api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment createComment(Long postId, Comment newComment) {
//        retrieve post by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
//        set post to comment
        newComment.setPost(post);
//        save comment
        return commentRepository.save(newComment);
    }

    @Override
    public List<Comment> getCommentByPostId(Long postId) {

//        retrieve comment by post id
        return commentRepository.findByPostId(postId);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    public Comment updateComment(Long postId, Long commentId, Comment updatedComment) {

        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        comment.setName(updatedComment.getName());
        comment.setEmail(updatedComment.getEmail());
        comment.setBody(updatedComment.getBody());
        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(Long postId, Long commentId) {

//        retrieve post by id
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
//        retrieve comments by comment id
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "commentId", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return comment;
    }
}
// http://localhost:8080/api/posts           -get        -all post
// http://localhost:8080/api/posts/{id}      -get        -single post
// http://localhost:8080/api/posts           -post        -create post
// http://localhost:8080/api/posts/{id}      -put         -update post
// http://localhost:8080/api/posts/{id}      -delete      -delete post

// http://localhost:8080/api/posts/{postId}/comments             -post       -create comment
// http://localhost:8080/api/posts/{postId}/comments            -get       -get all comment
// http://localhost:8080/api/posts/{postId}/comments/{commentId}    -get     -single comment
// http://localhost:8080/api/posts/{postId}/comments/{commentId}     -put    -update comment
// http://localhost:8080/api/posts/{postId}/comments/{commentId}     -delete    -delete comment
