package org.wcci.blog;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HashtagRepository extends CrudRepository<Hashtag, Long> {
    Optional<Hashtag> findByName(String name);
}
