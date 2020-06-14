package com.fitnessfreak.exercise;

public class ExerciseModel {
    private String title;
    private String image;
    private String video;
    private String description;

    public ExerciseModel(String title, String image, String video, String description) {
        this.title = title;
        this.image = image;
        this.video = video;
        this.description = description;
    }

    public ExerciseModel() {
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
