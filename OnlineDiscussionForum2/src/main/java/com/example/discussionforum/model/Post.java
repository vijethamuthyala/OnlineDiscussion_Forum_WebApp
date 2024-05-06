package com.example.discussionforum.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Lob
    private String description;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Thread> threads = new ArrayList<>();

    // Constructors, getters, and setters
    public Post() {
        // Default constructor
    }

    public Post(String title, Date date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }
}
