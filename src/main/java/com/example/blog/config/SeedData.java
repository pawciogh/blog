package com.example.blog.config;

import com.example.blog.models.Post;
import com.example.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Override
    public void run(String... args) throws Exception {

        List<Post> posts = postService.getAll();

        if (posts.isEmpty()) { // Check if there are no existing posts

            for (int i = 0; i < 10; i++) { // Create 5 random posts
                Post post = new Post();
                post.setTitle("Post " + (i + 1)); // Generate a random title
                post.setBody(RandomStringUtils.randomAlphabetic(100)); // Generate a random body
                postService.save(post); // Save the post
            }
        }
    }

}