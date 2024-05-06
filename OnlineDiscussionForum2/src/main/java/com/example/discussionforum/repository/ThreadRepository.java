package com.example.discussionforum.repository;

import com.example.discussionforum.model.Post;
import com.example.discussionforum.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreadRepository extends JpaRepository<Thread, Long> {
    List<Thread> findByPost(Post post);
}
