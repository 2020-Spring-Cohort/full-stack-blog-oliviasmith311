package org.wcci.blog.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Hashtag;

import java.util.Optional;

public interface HashtagRepository extends CrudRepository<Hashtag, Long> {
    Optional<Hashtag> findByName(String name);
}
