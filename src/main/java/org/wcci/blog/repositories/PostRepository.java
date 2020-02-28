package org.wcci.blog.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.wcci.blog.models.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
//    Collection<Post> findAllPosts();

    Post findPostByTitle(String title);
}

