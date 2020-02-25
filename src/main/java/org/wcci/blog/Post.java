package org.wcci.blog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Post {


    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @ManyToOne
    private Author author;

    public Post() {
    }

    public Post(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public Post(String title){
        this.title = title;
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(title, post.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }


}

