package com.example.pintv20;

public class ListItem {
    int Image;
    String comment;
    String date;
    String time;

    public ListItem(int image, String comment, String date, String time) {
        Image = image;
        this.comment = comment;
        this.date = date;
        this.time = time;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
