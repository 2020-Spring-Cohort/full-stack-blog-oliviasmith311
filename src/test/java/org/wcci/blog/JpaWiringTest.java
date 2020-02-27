package org.wcci.blog;

import jdk.jfr.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;


import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DirtiesContext
@DataJpaTest
public class JpaWiringTest {

    @Autowired
    private PostRepository postRepo;
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private CategoryRepository catRepo;
    @Autowired
    private HashtagRepository hashtagRepo;
    @Autowired
    private TestEntityManager entityManager;
    private Author testAuthor;
    private PostCategory testCat;

    @BeforeEach
    void setUp() {
        testAuthor = new Author("testName");
        testCat = new PostCategory("testTitle");
        authorRepo.save(testAuthor);
        catRepo.save(testCat);
    }

    @Test
    public void postShouldSaveToPostRepo(){
        Post testPost = new Post("test title", testAuthor, testCat);
        postRepo.save(testPost);

        entityManager.flush();
        entityManager.clear();

        Optional<Post> retrievedPostOptional = postRepo.findById(testPost.getId());

        Post retrievedPost = retrievedPostOptional.get();

        assertThat(retrievedPost).isEqualTo(testPost);
    }

    @Test
    public void postShouldHaveAuthor(){
        Post testPost = new Post("test title", testAuthor, testCat);
        Author retrievedAuthor = testPost.getAuthor();
        assertThat(retrievedAuthor).isEqualTo(testAuthor);
    }

    @Test
    public void authorShouldHavePosts(){
        authorRepo.save(testAuthor);
        Post testPost = new Post("test title", testAuthor, testCat);
        postRepo.save(testPost);

        entityManager.flush();
        entityManager.clear();

        Author retrievedAuthor = authorRepo.findById(testAuthor.getId()).get();

        assertThat(retrievedAuthor.getPosts()).contains(testPost);
    }

    @Test
    public void postShouldHaveCategory(){
        Post testPost = new Post("test title", testAuthor, testCat);
        PostCategory retrievedCat = testPost.getCategory();
        assertThat(retrievedCat).isEqualTo(testCat);
    }

    @Test
    public void categoryShouldHavePosts(){
        authorRepo.save(testAuthor);
        catRepo.save(testCat);
        Post testPost = new Post("test title", testAuthor, testCat);
        postRepo.save(testPost);

        entityManager.flush();
        entityManager.clear();

        PostCategory retrievedCat = catRepo.findById(testCat.getId()).get();

        assertThat(retrievedCat.getPosts()).contains(testPost);
    }

    @Test
    public void postShouldHaveHashtags(){
        Post testPost = new Post("test title", testAuthor, testCat);
        postRepo.save(testPost);
        Hashtag testHashtag = new Hashtag("cool");
        hashtagRepo.save(testHashtag);
        testPost.addHashtag(testHashtag);
        postRepo.save(testPost);

        entityManager.flush();
        entityManager.clear();

        Post retrievedPost = postRepo.findById(testPost.getId()).get();
        assertThat(retrievedPost.getHashtags()).contains(testHashtag);
    }

    @Test
    public void hashtagsShouldHavePosts(){
        Post testPost = new Post("test title", testAuthor, testCat);
        postRepo.save(testPost);
        Hashtag testHashtag = new Hashtag("great");
        hashtagRepo.save(testHashtag);

        Hashtag retrievedHashtag = hashtagRepo.findByName(testHashtag.getName()).get();
        assertThat(retrievedHashtag).isEqualTo(testHashtag);
    }
}
