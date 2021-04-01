package com.irek.conference.entity;

import javax.persistence.*;
import java.util.Objects;

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

//    public void setId(Integer id) {
//        this.id = id;
//    }

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

    @Override
    public int hashCode() {
        return Objects.hashCode(id) + 31 * Objects.hashCode(presentation_name);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(obj.getClass() != this.getClass())
            return false;

        Presentation presentation = (Presentation) obj;

        return this.id == presentation.id && this.presentation_name.equals(presentation.presentation_name);
    }

    @Override
    public String toString() {
        return id + " " + presentation_name + " " + author.getLogin();
    }
}
