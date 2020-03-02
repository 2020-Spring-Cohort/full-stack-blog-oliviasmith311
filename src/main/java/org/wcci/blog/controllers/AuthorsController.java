package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.PostCategory;
import org.wcci.blog.repositories.AuthorRepository;
import org.wcci.blog.repositories.CategoryRepository;

import java.util.Optional;

@Controller
public class AuthorsController {
    private AuthorRepository authorRepo;

    public AuthorsController(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @RequestMapping("/author/{name}")
    public String displayAuthor(@PathVariable String name, Model model) {
        Optional<Author> retrievedAuthor = authorRepo.findAuthorByName(name);
        model.addAttribute("author", retrievedAuthor.get());
        return "single-author";
    }

    @RequestMapping("/authors")
    public String displayAllAuthors(Model model){
        model.addAttribute("authors", authorRepo.findAll());
        return "authors";
    }
}