package com.sanket.blogapp.services.impl;

import com.sanket.blogapp.entities.Category;
import com.sanket.blogapp.entities.Post;
import com.sanket.blogapp.entities.User;
import com.sanket.blogapp.payloads.PostDto;
import com.sanket.blogapp.repositories.CategoryRepo;
import com.sanket.blogapp.repositories.PostRepo;
import com.sanket.blogapp.repositories.UserRepo;
import com.sanket.blogapp.services.PostService;
import com.sanket.blogapp.util.ResponseObject;
import com.sanket.blogapp.util.Util;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private ModelMapper modelMapper;
    private PostRepo postRepo;
    private UserRepo userRepo;
    private CategoryRepo categoryRepo;

    @Override
    public ResponseObject createPost(PostDto postDto, Long userId, Long categoryId) {
        Post post = new Post();

        if (this.userRepo.findById(userId).isPresent() && this.categoryRepo.findById(categoryId).isPresent()) {
            post.setUser(this.userRepo.findById(userId).get());
            post.setCategory(this.categoryRepo.findById(categoryId).get());
            post.setDate(new Date());
            post.setContent(postDto.getContent());
            post.setTitle(postDto.getTitle());
            post.setImageName("default.png");
            post = this.postRepo.save(post);
            PostDto postDto1 = this.modelMapper.map(post, PostDto.class);
            return Util.resourceCreated(postDto1, "Post");
        } else {
            return Util.resourceNotCreated(null, "Post");
        }

    }

    @Override
    public ResponseObject updatePost(PostDto postDto, Long id) {
        if (this.postRepo.findById(id).isPresent()) {
            Post post = this.postRepo.findById(id).get();
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());
            this.postRepo.save(post);
            PostDto postDto1 = this.modelMapper.map(post, PostDto.class);
            return Util.resourceUpdated(postDto1, "Post", id);
        } else {
            return Util.resourceNotUpdated(null, "Post", id);
        }
    }

    @Override
    public ResponseObject deletePost(Long id) {

        if (this.postRepo.findById(id).isPresent()) {
            this.postRepo.deleteById(id);
            return Util.resourceDeleted("Post", id);
        } else {
            return Util.resourceNotDeleted("Post");
        }
    }

    @Override
    public ResponseObject getPost(Long id) {
        if (this.postRepo.findById(id).isPresent()) {
            Post post = this.postRepo.findById(id).get();
            PostDto postDto = this.modelMapper.map(post, PostDto.class);
            return Util.resourceFound(postDto, "Post");

        } else {
            return Util.resourceNotFound("Post");
        }
    }

    @Override
    public ResponseObject getAllPosts() {
        List<Post> posts = this.postRepo.findAll();
        if (!posts.isEmpty()) {
            List<PostDto> postDtos = new ArrayList<>();
            for (Post post : posts) {
                PostDto postDto = this.modelMapper.map(post, PostDto.class);
                postDtos.add(postDto);
            }
            return Util.resourceFound(postDtos, "Post");
        } else {
            return Util.resourceNotFound("Post");
        }

    }

    @Override
    public ResponseObject getPostsByCategory(Long categoryId) {
        if (this.categoryRepo.findById(categoryId).isPresent()
                && !this.postRepo.findByCategory(this.categoryRepo.findById(categoryId).get()).isEmpty()
        ) {
            Category category = this.categoryRepo.findById(categoryId).get();
            List<Post> posts = this.postRepo.findByCategory(category);
            List<PostDto> postDtos = new ArrayList<>();
            for (Post post : posts) {
                PostDto postDto = this.modelMapper.map(post, PostDto.class);
                postDtos.add(postDto);
            }
            return Util.resourceFound(postDtos, "Posts");
        } else {
            return Util.resourceNotFound("Posts");
        }
    }

    @Override
    public ResponseObject getPostsByUser(Long userId) {

        if (
                this.userRepo.findById(userId).isPresent()
                        && !this.postRepo.findByUser(this.userRepo.findById(userId).get()).isEmpty()
        ) {
            User user = this.userRepo.findById(userId).get();
            List<Post> posts = this.postRepo.findByUser(user);
            List<PostDto> postDtos = new ArrayList<>();
            for (Post post : posts) {
                PostDto postDto = this.modelMapper.map(post, PostDto.class);
                postDtos.add(postDto);
            }
            return Util.resourceFound(postDtos, "Posts");

        } else {
            return Util.resourceNotFound("Posts");
        }
    }

    @Override
    public ResponseObject searchPostsByTitle(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContainingIgnoreCase(keyword);
        if (!posts.isEmpty()) {
            List<PostDto> postDtos = new ArrayList<>();
            for (Post post : posts) {
                PostDto postDto = this.modelMapper.map(post, PostDto.class);
                postDtos.add(postDto);
            }
            return Util.resourceFound(postDtos, "Posts");
        } else {
            return Util.resourceNotFound("Post");
        }
    }


}
