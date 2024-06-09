package com.yogesh.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class ReviewMessage {

    public Integer id;

    public Integer bookId;

    public String comment;

    public Float rating;

    public ReviewMessage(){

    }

    public ReviewMessage(Integer id, Integer bookId, String comment, Float rating) {
        this.id = id;
        this.bookId = bookId;
        this.comment = comment;
        this.rating = rating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
