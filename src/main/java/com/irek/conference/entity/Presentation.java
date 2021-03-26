package com.irek.conference.entity;

import javax.persistence.*;

@Entity
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String presentation_name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "presenter_id")
    private User author;

    public Presentation() {
    }

    public Presentation(String presentation_name, User author) {
        this.presentation_name = presentation_name;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPresentation_name() {
        return presentation_name;
    }

    public void setPresentation_name(String nameOfPresentation) {
        this.presentation_name = nameOfPresentation;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


}
