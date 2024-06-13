package com.example.blog.config;

import com.example.blog.models.Account;
import com.example.blog.models.Post;
import com.example.blog.services.AccountService;
import com.example.blog.services.PostService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService; // Inject AccountService

    @Override
    public void run(String... args) throws Exception {

        List<Post> posts = postService.getAll();

        if (posts.isEmpty()) { // Check if there are no existing posts
            Faker faker = new Faker();

            // Create an Account
            Account account = new Account();
            // Set properties for the account here
            accountService.save(account); // Save the Account

            for (int i = 0; i < 10; i++) { // Create 10 random posts
                Post post = new Post();
                post.setTitle(faker.book().title()); // Generate a random title
                post.setBody(faker.lorem().paragraph()); // Generate a random body
                post.setAccount(account); // Set the account to the post
                postService.save(post); // Save the post
            }
        }
    }

}