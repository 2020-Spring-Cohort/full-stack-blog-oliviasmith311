package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Hashtag;
import org.wcci.blog.models.PostCategory;
import org.wcci.blog.repositories.CategoryRepository;
import org.wcci.blog.repositories.HashtagRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class HashtagController {
    private HashtagRepository hashtagRepo;

    public HashtagController(HashtagRepository hashtagRepo) {
        this.hashtagRepo = hashtagRepo;
    }

    @RequestMapping("/hashtags")
    public String displayHashtags(Model model){
        model.addAttribute("allHashtags", hashtagRepo.findAll());
        return "tags";
    }

    @RequestMapping("/hashtag/{hashtagName}")
    public String displayAllPostsWithHashtag(@PathVariable String hashtagName, Model model) {
        Hashtag retrievedHashtag = hashtagRepo.findByName(hashtagName).get();
        model.addAttribute("hashtag", retrievedHashtag);
        return "single-tag";
    }
}
