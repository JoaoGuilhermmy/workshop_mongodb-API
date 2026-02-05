package com.joaoguilhermmy.workshopmongo.dto;

import java.io.Serializable;
import java.time.Instant;

public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    private String id;
    private Instant moment;
    private String text;
    private AuthorDTO author;

    public CommentDTO() {
    }

    public CommentDTO(String id, Instant moment, String text, AuthorDTO author) {
        this.id = id;
        this.moment = moment;
        this.text = text;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

}
