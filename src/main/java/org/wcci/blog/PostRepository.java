package org.wcci.blog;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PostRepository extends CrudRepository<Post, Long> {
    Collection<Post> findAllPosts();

    Post findPostByTitle(String title);
}

