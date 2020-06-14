package com.fitnessfreak.exercise;


import android.os.Parcel;
import android.os.Parcelable;

public class ExerciseModel implements Parcelable {

    protected ExerciseModel(Parcel in) {
        title = in.readString();
        image = in.readString();
        video = in.readString();
        description = in.readString();
        difficulty = in.readString();
        targetMuscle = in.readString();
        benefit = in.readString();
        duration = in.readString();
    }

    public static final Creator<ExerciseModel> CREATOR = new Creator<ExerciseModel>() {
        @Override
        public ExerciseModel createFromParcel(Parcel in) {
            return new ExerciseModel(in);
        }

        @Override
        public ExerciseModel[] newArray(int size) {
            return new ExerciseModel[size];
        }
    };

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTargetMuscle() {
        return targetMuscle;
    }

    public void setTargetMuscle(String targetMuscle) {
        this.targetMuscle = targetMuscle;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ExerciseModel(String title, String image, String video, String description, String difficulty, String targetMuscle, String benefit, String duration) {
        this.title = title;
        this.image = image;
        this.video = video;
        this.description = description;
        this.difficulty = difficulty;
        this.targetMuscle = targetMuscle;
        this.benefit = benefit;
        this.duration = duration;
    }

    private String title;
    private String image;
    private String video;
    private String description;
    private String difficulty;
    private String targetMuscle;
    private String benefit;
    private String duration;




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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(image);
        parcel.writeString(video);
        parcel.writeString(description);
        parcel.writeString(difficulty);
        parcel.writeString(targetMuscle);
        parcel.writeString(benefit);
        parcel.writeString(duration);


    }
}
