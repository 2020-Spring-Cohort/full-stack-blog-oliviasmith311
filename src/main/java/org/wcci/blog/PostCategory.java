package org.wcci.blog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class PostCategory {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public PostCategory(){
    }

    @OneToMany(mappedBy = "postCategory")
    private Collection<Post> posts;

    public Collection<Post> getPosts() {
        return posts;
    }
}
