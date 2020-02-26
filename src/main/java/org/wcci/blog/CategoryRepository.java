package org.wcci.blog;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<PostCategory, Long> {
}
