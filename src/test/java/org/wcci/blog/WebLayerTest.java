package org.wcci.blog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class WebLayerTest {

    @MockBean
    PostRepository mockRepo;
    @MockBean
    AuthorRepository authorRepo;
    @MockBean
    CategoryRepository categoryRepo;
    @MockBean
    HashtagRepository hashtagRepo;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void postsShouldBeOKAndReturnTheIndexWithPostsModelAttribute() throws Exception {
        mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("posts"));
    }

    @Test
    public void shouldReceiveOKFromSinglePostEndpoint() throws Exception {
        Post testPost = new Post();
        when(mockRepo.findPostByTitle("test title")).thenReturn(testPost);
        mockMvc.perform(get("/posts/test title"))
                .andExpect(status().isOk())
                .andExpect(view().name("singlepost"))
                .andExpect(model().attributeExists("post"));
    }

}
