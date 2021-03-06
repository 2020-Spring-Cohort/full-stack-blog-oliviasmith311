package org.wcci.blog.models;

import org.wcci.blog.models.Post;

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
    private String title;

    public Long getId() {
        return id;
    }

    public PostCategory(){
    }

    public String getTitle() {
        return title;
    }

    public PostCategory(String title){
        this.title = title;
    }

    @OneToMany(mappedBy = "postCategory")
    private Collection<Post> posts;

    public Collection<Post> getPosts() {
        return posts;
    }
}
