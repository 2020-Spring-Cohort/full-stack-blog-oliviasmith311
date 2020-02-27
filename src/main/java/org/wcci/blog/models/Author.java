package org.wcci.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Author {

    private String name;

    public Author(){
    }

    public String getName() {
        return name;
    }

    public Author(String name){
        this.name = name;
    }

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
