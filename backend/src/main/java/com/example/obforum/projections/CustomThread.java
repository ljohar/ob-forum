package com.example.obforum.projections;

import org.springframework.beans.factory.annotation.Value;

public interface CustomThread {

    String getTitle();

    @Value("#{target.getPosts().size()}")
    int getPostsCount();
}
