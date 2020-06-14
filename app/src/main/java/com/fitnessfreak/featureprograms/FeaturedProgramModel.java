package com.fitnessfreak.featureprograms;

public class FeaturedProgramModel {
    private String title;

    private String image_url;

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

    public String getimage_url() {
        return image_url;
    }

    public void setimage_url(String image_url) {
        this.image_url = image_url;
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

    public FeaturedProgramModel(String title, String image_url, String video, String description,
                                String duration, String difficulty, String benefits) {
        super();
        this.title = title;
        this.image_url = image_url;
        this.video = video;
        this.description = description;
        this.duration = duration;
        this.difficulty = difficulty;
        this.benefits = benefits;
    }

    public FeaturedProgramModel() {
        super();
    }
}
