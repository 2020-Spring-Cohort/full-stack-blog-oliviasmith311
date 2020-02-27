package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.PostCategory;
import org.wcci.blog.repositories.AuthorRepository;
import org.wcci.blog.repositories.CategoryRepository;
import org.wcci.blog.repositories.HashtagRepository;
import org.wcci.blog.repositories.PostRepository;

@Controller
public class PostController {

    private PostRepository postRepo;
    private AuthorRepository authorRepo;
    private CategoryRepository categoryRepo;
    private HashtagRepository hashtagRepo;
    private Author testAuthor;
    private PostCategory testCat;

    public PostController(PostRepository postRepo, AuthorRepository authorRepo, CategoryRepository categoryRepo, HashtagRepository hashtagRepo){
        this.postRepo = postRepo;
        this.authorRepo = authorRepo;
        this.categoryRepo = categoryRepo;
        this.hashtagRepo = hashtagRepo;
    }

    @RequestMapping({"/posts", "/", ""})
    public String displayPosts(Model model){
        model.addAttribute("posts", postRepo.findAll());
        return "index";
    }

    @GetMapping("/posts/{title}")
    public String displaySinglePost(@PathVariable String title, Model model) {
        Post retrievedPost = postRepo.findPostByTitle(title);
        model.addAttribute("post", retrievedPost);

        return "singlepost";
    }

    @PostMapping("/add-post")
    public String addPost(@RequestParam String title) {
        postRepo.save(new Post(title, testAuthor, testCat));
        return "redirect:posts";
    }
}
