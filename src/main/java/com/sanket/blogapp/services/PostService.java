package com.sanket.blogapp.services;

import com.sanket.blogapp.payloads.PostDto;
import com.sanket.blogapp.util.ResponseObject;

public interface PostService {

    ResponseObject createPost(PostDto postDto, Long userId, Long categoryId);
    ResponseObject updatePost(PostDto postDto, Long id);
    ResponseObject deletePost(Long id);
    ResponseObject getPost(Long id);
    ResponseObject getAllPosts();
    ResponseObject getPostsByCategory(Long categoryId);
    ResponseObject getPostsByUser(Long userId);
    ResponseObject searchPostsByTitle(String keyword);

}
