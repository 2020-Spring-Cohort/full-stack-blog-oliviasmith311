package org.wcci.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {

    private PostRepository postRepo;
    private AuthorRepository authorRepo;
    private CategoryRepository categoryRepo;
    private HashtagRepository hashtagRepo;

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
}
