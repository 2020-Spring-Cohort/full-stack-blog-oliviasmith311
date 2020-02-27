package org.wcci.blog.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
//    Collection<Post> findAllPosts();

    Post findPostByTitle(String title);
}

