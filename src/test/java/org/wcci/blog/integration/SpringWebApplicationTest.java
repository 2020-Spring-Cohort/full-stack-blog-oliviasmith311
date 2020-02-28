package org.wcci.blog.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.PostCategory;
import org.wcci.blog.repositories.AuthorRepository;
import org.wcci.blog.repositories.CategoryRepository;
import org.wcci.blog.repositories.HashtagRepository;
import org.wcci.blog.repositories.PostRepository;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@SpringBootTest
@AutoConfigureMockMvc
public class SpringWebApplicationTest {

    @MockBean
    AuthorRepository authorRepo;
    @MockBean
    CategoryRepository categoryRepo;
    @MockBean
    HashtagRepository hashtagRepo;
    @MockBean
    private PostRepository postRepo;
    @Autowired
    private MockMvc mockMvc;

    PostCategory testCat;
    Author testAuthor;

    @Test
    public void shouldReceiveOKFromPostsEndpoint() throws Exception {
        mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReceiveOKFromSinglePostEndpoint() throws Exception {
        Post testPost = new Post("test title", testAuthor, testCat, "testBody");
        when(postRepo.findPostByTitle("test title")).thenReturn(testPost);

        mockMvc.perform(get("/posts/test title"))
                .andExpect(status().isOk());
    }
}