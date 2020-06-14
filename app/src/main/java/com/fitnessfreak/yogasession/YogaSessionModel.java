package com.fitnessfreak.yogasession;

public class YogaSessionModel {
    private String title;
    private String image;

    public YogaSessionModel(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public YogaSessionModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
