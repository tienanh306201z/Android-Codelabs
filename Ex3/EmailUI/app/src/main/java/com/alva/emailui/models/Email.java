package com.alva.emailui.models;

public class Email {
    private String title;
    private String description1;
    private String description2;
    private String avatarChar;
    private String avatarColor;
    private String time;

    public Email(String title, String description1, String description2, String avatarChar, String avatarColor, String time) {
        this.title = title;
        this.description1 = description1;
        this.description2 = description2;
        this.avatarChar = avatarChar;
        this.avatarColor = avatarColor;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getAvatarChar() {
        return avatarChar;
    }

    public void setAvatarChar(String avatarChar) {
        this.avatarChar = avatarChar;
    }

    public String getAvatarColor() {
        return avatarColor;
    }

    public void setAvatarColor(String avatarColor) {
        this.avatarColor = avatarColor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
