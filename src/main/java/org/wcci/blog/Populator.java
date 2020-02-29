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

        Author author2 = new Author("Olivia Smith");
        authorRepo.save(author2);
        PostCategory category2 = new PostCategory("Thoughts & Opinions");
        catRepo.save(category2);
        Post post2 = new Post("Th Joys Of The McElroys", author2, category2, "These boys arereat!");
        postRepo.save(post2);
        Hashtag hashtag2 = new Hashtag("Cool");
        hashtagRepo.save(hashtag2);
        post2.addHashtag(hashtag2);
        postRepo.save(post2);

        Author author3 = new Author("Olivia Smith");
        authorRepo.save(author3);
        PostCategory category3 = new PostCategory("Thoughts & Opinions");
        catRepo.save(category3);
        Post post3 = new Post("The Joys Ohe McElroys", author3, category3, "These s are great!");
        postRepo.save(post3);
        Hashtag hashtag3 = new Hashtag("cool");
        hashtagRepo.save(hashtag3);
        post3.addHashtag(hashtag3);
        postRepo.save(post3);

        Author author4 = new Author("Olivia Smith");
        authorRepo.save(author4);
        PostCategory category4 = new PostCategory("Thoughts & Opinions");
        catRepo.save(category4);
        Post post4 = new Post("TJoys Of The McElroys", author4, category4, "Thboys are great!");
        postRepo.save(post4);
        Hashtag hashtag4 = new Hashtag("cool");
        hashtagRepo.save(hashtag4);
        post4.addHashtag(hashtag4);
        postRepo.save(post4);

        Author author5 = new Author("Olivia Smith");
        authorRepo.save(author5);
        PostCategory category5 = new PostCategory("Thoughts & Opinions");
        catRepo.save(category5);
        Post post5 = new Post("The Joys OElroys", author5, category5, "These boys eat!");
        postRepo.save(post5);
        Hashtag hashtag5 = new Hashtag("cool");
        hashtagRepo.save(hashtag5);
        post5.addHashtag(hashtag5);
        postRepo.save(post5);
    }
}
