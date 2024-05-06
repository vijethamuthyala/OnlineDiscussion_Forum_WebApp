package com.example.discussionforum.repository;

import com.example.discussionforum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // You can define additional query methods here if needed
}
