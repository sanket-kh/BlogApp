package com.sanket.blogapp.repositories;

import com.sanket.blogapp.entities.Category;
import com.sanket.blogapp.entities.Post;
import com.sanket.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
    List<Post> findByCategory(Category category);

    @Query()
    List<Post> findByUser(User user);


    List<Post> findByTitleContainingIgnoreCase(String keyword);

}
