package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.PostCategory;
import org.wcci.blog.repositories.AuthorRepository;
import org.wcci.blog.repositories.CategoryRepository;
import org.wcci.blog.repositories.HashtagRepository;
import org.wcci.blog.repositories.PostRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

class CategoryControllerTest {

    private CategoryRepository mockStorage;
    private CategoryController underTest;
    private Model model;
    //    private BookStorage mockStorage;
    PostCategory testCat = new PostCategory();
    private AuthorRepository authorRepo;
    private PostRepository postRepo;
    private HashtagRepository hashtagRepo;
    Author testAuthor = new Author();
    Post testPost = new Post("test title", testAuthor, testCat);

    @BeforeEach
    void setUp() {
        mockStorage = mock(CategoryRepository.class);
        underTest = new CategoryController(mockStorage);
        hashtagRepo = mock(HashtagRepository.class);
        model = mock(Model.class);
        testCat = new PostCategory("testing title");
        when(mockStorage.findCategoryById(1L)).thenReturn(testCat);

    }

    @Test
    public void displayCategoryReturnsSingleCategoryTemplate() {
        String result = underTest.displayCategory(1L, model);
        assertThat(result).isEqualTo("single-category");
    }

    @Test
    public void displayCategoryInteractsWithDependenciesCorrectly() {
        underTest.displayCategory(1L, model);
        verify(mockStorage).findCategoryById(1L);
        verify(model).addAttribute("category", testCat);
    }


    @Test
    public void displayCategoryMappingIsCorrect() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("single-category"))
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attribute("category", testCat));
    }
}
