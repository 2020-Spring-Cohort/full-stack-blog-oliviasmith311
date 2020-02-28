package org.wcci.blog.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.ui.Model;
import org.wcci.blog.models.PostCategory;

public interface CategoryRepository extends CrudRepository<PostCategory, Long> {
    PostCategory findCategoryById(Long id);
}
