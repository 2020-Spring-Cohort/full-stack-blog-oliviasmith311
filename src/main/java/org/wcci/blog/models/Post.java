package org.wcci.blog.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    @ManyToOne
    private Author author;
    @ManyToOne
    private PostCategory postCategory;
    @ManyToMany
    private Collection<Hashtag> hashtags;

    private String postBody;

    public Post() {
    }

    public String getPostBody() {
        return postBody;
    }

    public Post(String title, Author author, PostCategory postCategory, String postBody){
        this.title = title;
        this.author = author;
        this.postCategory = postCategory;
        this.hashtags = new HashSet<>();
        this.postBody = postBody;
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

    public void addHashtag(Hashtag hashtagToAdd) {
        hashtags.add(hashtagToAdd);
    }

    public PostCategory getCategory() {
        return postCategory;
    }

    public Collection<Hashtag> getHashtags() {
            return hashtags;
    }
}


