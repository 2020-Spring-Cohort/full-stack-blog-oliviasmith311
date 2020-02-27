package org.wcci.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {

    private PostRepository postRepo;

    public PostController(PostRepository postRepo, AuthorRepository authorRepo, CategoryRepository categoryRepo, HashtagRepository hashtagRepo){
        this.postRepo = postRepo;
    }

    @RequestMapping({"/posts", "/", ""})
    public String displayPosts(Model model){
        model.addAttribute("posts", postRepo.findAllPosts());
        return "index";
    }

    @GetMapping("/posts/{title}")
    public String displaySinglePost(@PathVariable String title, Model model) {
        Post retrievedPost = postRepo.findPostByTitle(title);
        model.addAttribute("post", retrievedPost);

        return "categories";
    }
}
