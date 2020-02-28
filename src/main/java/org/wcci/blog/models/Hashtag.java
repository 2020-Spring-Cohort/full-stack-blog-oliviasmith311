package org.wcci.blog.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Hashtag {

    @ManyToMany(mappedBy = "hashtags")
    private Collection<Post> posts;

    private String name;
    public Hashtag(){}

    public Hashtag(String name){
        this.name = name;
        posts = new ArrayList<>();
    }
    @Id
    @GeneratedValue
    private Long id;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return Objects.equals(name, hashtag.name) &&
                Objects.equals(id, hashtag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public Collection <Post> getPosts(){
        return posts;
    }
}
