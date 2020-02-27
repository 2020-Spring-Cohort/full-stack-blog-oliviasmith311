package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.controllers.PostController;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.PostCategory;
import org.wcci.blog.repositories.AuthorRepository;
import org.wcci.blog.repositories.CategoryRepository;
import org.wcci.blog.repositories.HashtagRepository;
import org.wcci.blog.repositories.PostRepository;


import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerTest {

    private MockMvc mockMvc;
    private PostController underTest;
    private PostRepository postRepo;
    private Model mockModel;
    private AuthorRepository authorRepo;
    private CategoryRepository catRepo;
    private HashtagRepository hashtagRepo;
    Author testAuthor = new Author();
    PostCategory testCat = new PostCategory();

    @BeforeEach
    public void setUp() {
        mockModel = mock(Model.class);
        postRepo = mock(PostRepository.class);
        authorRepo = mock(AuthorRepository.class);
        catRepo = mock(CategoryRepository.class);
        hashtagRepo = mock(HashtagRepository.class);
        underTest = new PostController(postRepo, authorRepo, catRepo, hashtagRepo);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
    }

    @Test
    public void shouldReturnViewWithOnePost() {
        Post testPost = new Post("test title", testAuthor, testCat);
        when(postRepo.findPostByTitle("test title")).thenReturn(testPost);

        underTest.displaySinglePost("test title", mockModel);

        verify(postRepo).findPostByTitle("test title");
        verify(mockModel).addAttribute("post", testPost);
    }

    @Test
    public void shouldReturnViewNamedSinglePostWhenDisplaySinglePostIsCalled() {
        String viewName = underTest.displaySinglePost("test title", mockModel);
        assertThat(viewName).isEqualTo("singlepost");
    }

    @Test
    public void shouldGoToIndividualEndPoint() throws Exception {
        Post testPost = new Post("test title", testAuthor, testCat);
        when(postRepo.findPostByTitle("test title")).thenReturn(testPost);

        mockMvc.perform(get("/posts/test title"))
                .andExpect(status().isOk())
                .andExpect(view().name("singlepost"))
                .andExpect(model().attributeExists("post"))
                .andExpect(model().attribute("post", testPost));
    }

    @Test
    public void postsEndPointDisplaysAllPosts() throws Exception {
        Post testPost = new Post("test title", testAuthor, testCat);

        List<Post> postCollection = Collections.singletonList(testPost);
        when(postRepo.findAll()).thenReturn(postCollection);
        mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attribute("posts", postCollection));
    }

    @Test
    public void addPostShouldRedirectToPostsEndPoint() {
        String result = underTest.addPost("test title");
        assertThat(result).isEqualTo("redirect:posts");
    }

    @Test
    public void addPostShouldStoreANewPost() {
        underTest.addPost("test title");
        verify(postRepo).save(new Post("test title", testAuthor, testCat));
    }

    @Test
    public void addPostEndpointShouldAddNewPost() throws Exception {
        mockMvc.perform(post("/add-post")
                .param("title", "test title"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(postRepo).save(new Post("test title", testAuthor, testCat));
    }
}