package org.wcci.blog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Author {

    public Long getId() {
        return id;
    }

    @OneToMany(mappedBy = "author")
    private Collection<Post> posts;

    public Collection<Post> getPosts() {
        return posts;
    }

    @Id
    @GeneratedValue
    private Long id;

}
