package org.wcci.blog.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}