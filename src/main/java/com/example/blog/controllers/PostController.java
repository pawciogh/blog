package com.example.blog.controllers;
import com.example.blog.models.Post;
import com.example.blog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Controller
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {

        // find post by id
        Optional<Post> optionalPost = this.postService.getById(id);

        // if post exists put it in model
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            // Convert LocalDateTime to Date
            Date createdAt = Date.from(post.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
            Date updatedAt = Date.from(post.getUpdatedAt().atZone(ZoneId.systemDefault()).toInstant());

            model.addAttribute("post", post);
            model.addAttribute("createdAt", createdAt);
            model.addAttribute("updatedAt", updatedAt);

            return "post";
        } else {
            return "404";
        }
    }
}
