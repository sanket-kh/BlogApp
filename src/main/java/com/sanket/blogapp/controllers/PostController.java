package com.sanket.blogapp.controllers;


import com.sanket.blogapp.payloads.PostDto;
import com.sanket.blogapp.services.PostService;
import com.sanket.blogapp.util.ResponseObject;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<Object> createPost(
            @RequestBody PostDto postDto,
            @PathVariable("userId") Long userId,
            @PathVariable("categoryId") Long categoryId
    ){
        ResponseObject responseObject = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());

    }
    @GetMapping("/posts/")
    public ResponseEntity<Object> getAllPosts(){
        return new ResponseEntity<>(this.postService.getAllPosts(),this.postService.getAllPosts().getHttpStatus());
    }
    @PutMapping("/posts/{id}")
    public ResponseEntity<Object> updatePost(@RequestBody PostDto postDto, @PathVariable("id") Long id){
        ResponseObject responseObject = this.postService.updatePost(postDto,id);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Object> deletePost(@PathVariable Long id){
       ResponseObject responseObject= this.postService.deletePost(id);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }
    @GetMapping("/posts/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable Long id){
        return new ResponseEntity<>(this.postService.getPost(id),this.postService.getPost(id).getHttpStatus());
    }
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<Object> getPostsByUser(@PathVariable("userId") Long userId){
        ResponseObject responseObject = this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<Object> getPostByCategory(@PathVariable("categoryId") Long categoryId){
        ResponseObject responseObject = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(responseObject, responseObject.getHttpStatus());
    }
    @GetMapping("/search/posts/{keyword}")
    public ResponseEntity<Object> searchPostsByTitle(@PathVariable("keyword") String keyword){
        ResponseObject responseObject = this.postService.searchPostsByTitle(keyword);
        return new ResponseEntity<>(responseObject,responseObject.getHttpStatus());
    }







}
