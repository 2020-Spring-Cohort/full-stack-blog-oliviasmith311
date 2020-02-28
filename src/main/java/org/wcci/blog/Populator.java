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
        Post post1 = new Post("The Joys Of The McElroys", author1, category1);
        postRepo.save(post1);


    }
}
