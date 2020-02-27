package org.wcci.blog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

public interface PostRepository extends CrudRepository<Post, Long> {
//    Collection<Post> findAllPosts();

    Post findPostByTitle(String title);
}

