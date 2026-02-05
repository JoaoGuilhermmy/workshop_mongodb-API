package com.joaoguilhermmy.workshopmongo.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    private Date date;
    private String text;
    private AuthorDTO author;

    public CommentDTO() {
    }

    public CommentDTO(String text, Date date, AuthorDTO author) {
        this.date = date;
        this.text = text;
        this.author = author;
    }

    public Date date() {
        return date;
    }

    public void setMoment(Date date) {
        this.date = date;
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
