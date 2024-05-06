package com.example.discussionforum.service;

import com.example.discussionforum.model.Post;
import com.example.discussionforum.model.Thread;

import java.util.List;

public interface ThreadService {

    void createThread(Thread thread);

    List<Thread> getAllThreadsByPost(Post post);
}
