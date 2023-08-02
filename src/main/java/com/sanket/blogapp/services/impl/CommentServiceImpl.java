package com.sanket.blogapp.services.impl;

import com.sanket.blogapp.entities.Comment;
import com.sanket.blogapp.payloads.CommentDto;
import com.sanket.blogapp.repositories.CommentRepo;
import com.sanket.blogapp.repositories.PostRepo;
import com.sanket.blogapp.repositories.UserRepo;
import com.sanket.blogapp.services.CommentService;
import com.sanket.blogapp.util.ResponseObject;
import com.sanket.blogapp.util.Util;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepo commentRepo;
    private ModelMapper modelMapper;
    private UserRepo userRepo;
    private PostRepo postRepo;


    @Override
    public ResponseObject createComment(CommentDto commentDto, Long userId, Long postId) {
        if (this.userRepo.findById(userId).isPresent() && this.postRepo.findById(postId).isPresent()) {
            try {
                Comment comment = this.modelMapper.map(commentDto, Comment.class);
                comment.setUser(this.userRepo.findById(userId).get());
                comment.setPost(this.postRepo.findById(postId).get());
                comment = this.commentRepo.save(comment);
                commentDto = this.modelMapper.map(comment, CommentDto.class);
                return Util.resourceCreated(commentDto,"Comment");
            }catch (Exception e){
                return Util.resourceNotCreated(null, "Comment");
            }
        } else {
            return Util.resourceNotCreated(null,"Comment");
        }
    }

    @Override
    public ResponseObject updateComment(CommentDto commentDto, Long userId, Long postId, Long id) {
        if(this.commentRepo.findById(id).isPresent()
                && this.userRepo.findById(userId).isPresent()
                && this.postRepo.findById(postId).isPresent()
        ){
            Comment comment =  this.commentRepo.findById(id).get();
            comment.setComment(commentDto.getComment());
            comment = this.commentRepo.save(comment);
            commentDto = this.modelMapper.map(comment, CommentDto.class);
            return Util.resourceUpdated(commentDto, "Comment",id);
        }else {
            return Util.resourceNotUpdated(null, "Comment", id);
        }
    }

    @Override
    public ResponseObject deleteComment(Long userId, Long postId, Long id) {
        if(this.commentRepo.findById(id).isPresent()
                && this.userRepo.findById(userId).isPresent()
                && this.postRepo.findById(postId).isPresent() ){
            return Util.resourceDeleted("Comment",id);
        }else {
            return Util.resourceNotDeleted("Comment");
        }
    }

    @Override
    public ResponseObject getCommentById(Long id) {
        if (this.commentRepo.findById(id).isPresent()){
            return Util.resourceFound(this.commentRepo.findById(id).get(),"Comment");
        }else {
            return Util.resourceNotFound("Comment");
        }
    }

    @Override
    public ResponseObject getCommentByUser(Long userId) {
        if( this.userRepo.findById(userId).isPresent()
                && !this.commentRepo.findCommentsByUser(this.userRepo.findById(userId).get()).isEmpty()

        ){
            List<Comment> comments = this.commentRepo.findCommentsByUser(this.userRepo.findById(userId).get());
            List<CommentDto> commentDtos = new ArrayList<>();
            for (Comment comment:comments
                 ) {
                CommentDto commentDto = this.modelMapper.map(comment, CommentDto.class);
                commentDtos.add(commentDto);
            }
            return Util.resourceFound(commentDtos, "Comment");
        }else {
            return Util.resourceNotFound("Comments");
        }
    }

    @Override
    public ResponseObject getCommentByPost(Long postId) {

        if( this.postRepo.findById(postId).isPresent()
                && !this.commentRepo.findCommentsByPost(this.postRepo.findById(postId).get()).isEmpty()

        ){
            List<Comment> comments = this.commentRepo.findCommentsByPost(this.postRepo.findById(postId).get());
            List<CommentDto> commentDtos = new ArrayList<>();
            for (Comment comment:comments
            ) {
                CommentDto commentDto = this.modelMapper.map(comment, CommentDto.class);
                commentDtos.add(commentDto);
            }
            return Util.resourceFound(commentDtos, "Comment");
        }else {
            return Util.resourceNotFound("Comments");
        }    }
}
