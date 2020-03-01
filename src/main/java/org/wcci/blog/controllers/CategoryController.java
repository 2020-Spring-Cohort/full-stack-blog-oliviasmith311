package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.models.Hashtag;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.PostCategory;
import org.wcci.blog.repositories.CategoryRepository;
import org.wcci.blog.repositories.PostRepository;

import java.util.Optional;

@Controller
public class CategoryController {
    private CategoryRepository categoryRepo;

    public CategoryController(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @RequestMapping("/categories/{id}")
    public String displayCategory(@PathVariable Long id, Model model) {
        PostCategory retrievedCategory = categoryRepo.findCategoryById(id);
        model.addAttribute("category", retrievedCategory);
        return "single-category";
    }

    @RequestMapping("/categories")
    public String displayAllCategories(Model model){
        model.addAttribute("categories", categoryRepo.findAll());
        return "categories";
    }

    @PostMapping("/add-category")
    public String addCategoryToAllCategories(@RequestParam String title){
        PostCategory categoryToAdd;
        Optional<PostCategory> categoryToAddOpt = categoryRepo.findCategoryByTitle(title);
        if(categoryToAddOpt.isEmpty()){
            categoryToAdd = new PostCategory(title);
        }else{
            categoryToAdd = categoryToAddOpt.get();
        }
        categoryRepo.save(categoryToAdd);
        return "redirect:/categories";
    }

}
