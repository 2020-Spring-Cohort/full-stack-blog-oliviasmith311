package org.wcci.blog;

import jdk.jfr.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {

    @Autowired
    private PostRepository postRepo;
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void postShouldSaveToPostRepo(){
        Post testPost = new Post("Test Name");
        postRepo.save(testPost);

        entityManager.flush();
        entityManager.clear();

        Optional<Post> retrievedPostOptional = postRepo.findById(testPost.getId());

        Post retrievedPost = retrievedPostOptional.get();

        assertThat(retrievedPost).isEqualTo(testPost);
    }

    @Test
    public void postShouldHaveAuthor(){
        Author testAuthor = new Author();
        Post testPost = new Post("test title", testAuthor);
        Author retrievedAuthor = testPost.getAuthor();
        assertThat(retrievedAuthor).isEqualTo(testAuthor);
    }

    @Test
    public void authorShouldHavePosts(){
        Author testAuthor = new Author();
        authorRepo.save(testAuthor);
        Post testPost = new Post("test title", testAuthor);
        postRepo.save(testPost);

        entityManager.flush();
        entityManager.clear();

        Author retrievedAuthor = authorRepo.findById(testAuthor.getId()).get();

        assertThat(retrievedAuthor.getPosts()).contains(testPost);
    }
}
