package com.example.discussionforum.service;

import com.example.discussionforum.model.Post;
import com.example.discussionforum.model.Thread;
import com.example.discussionforum.repository.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;

    @Autowired
    public ThreadServiceImpl(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    @Override
    public void createThread(Thread thread) {
        threadRepository.save(thread);
    }

    @Override
    public List<Thread> getAllThreadsByPost(Post post) {
        return threadRepository.findByPost(post);
    }
}
