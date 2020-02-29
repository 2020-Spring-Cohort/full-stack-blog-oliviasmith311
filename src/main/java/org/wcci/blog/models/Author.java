package org.wcci.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name) &&
                Objects.equals(id, author.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
