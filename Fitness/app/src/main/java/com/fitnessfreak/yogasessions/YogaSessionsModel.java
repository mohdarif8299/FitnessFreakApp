package com.fitnessfreak.yogasessions;

public class YogaSessionsModel {
    private String title;

    private String image;

    private String video;

    private String description;

    private String duration;

    private String difficulty;

    private String benefits;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public YogaSessionsModel(String title, String image, String video, String description,
                             String duration, String difficulty, String benefits) {
        super();
        this.title = title;
        this.image = image;
        this.video = video;
        this.description = description;
        this.duration = duration;
        this.difficulty = difficulty;
        this.benefits = benefits;
    }

    public YogaSessionsModel() {
        super();
    }
}
