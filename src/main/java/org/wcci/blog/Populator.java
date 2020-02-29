package org.wcci.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Hashtag;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.PostCategory;
import org.wcci.blog.repositories.AuthorRepository;
import org.wcci.blog.repositories.CategoryRepository;
import org.wcci.blog.repositories.HashtagRepository;
import org.wcci.blog.repositories.PostRepository;

@Component
public class Populator implements CommandLineRunner {

    private AuthorRepository authorRepo;
    private CategoryRepository catRepo;
    private HashtagRepository hashtagRepo;
    private PostRepository postRepo;

    public Populator(AuthorRepository authorRepo, CategoryRepository catRepo, HashtagRepository hashtagRepo, PostRepository postRepo){
        this.authorRepo = authorRepo;
        this.catRepo = catRepo;
        this.hashtagRepo = hashtagRepo;
        this.postRepo = postRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Author author1 = new Author("Olivia Smith");
        authorRepo.save(author1);
        PostCategory category1 = new PostCategory("Thoughts & Opinions");
        catRepo.save(category1);
        Post post1 = new Post("The Joys Of The McElroys", author1, category1, "These boys are great!");
        postRepo.save(post1);
        Hashtag hashtag1 = new Hashtag("cool");
        hashtagRepo.save(hashtag1);
        post1.addHashtag(hashtag1);
        postRepo.save(post1);

        Post post2 = new Post("Torsy the Torso Horse", author1, category1, "He's just a torso");
        postRepo.save(post2);
        post2.addHashtag(hashtag1);
        postRepo.save(post2);

        Post post3 = new Post("Munch Squad", author1, category1, "These boys eat!");
        postRepo.save(post3);
        post3.addHashtag(hashtag1);
        postRepo.save(post3);

        Post post4 = new Post("I Wanna Munch", author1, category1, "Squad!");
        postRepo.save(post4);
        post4.addHashtag(hashtag1);
        postRepo.save(post4);

        Post post5 = new Post("Clint Fan Club", author1, category1, "Clint is the OG McElboy");
        postRepo.save(post5);
        post5.addHashtag(hashtag1);
        postRepo.save(post5);
    }
}
