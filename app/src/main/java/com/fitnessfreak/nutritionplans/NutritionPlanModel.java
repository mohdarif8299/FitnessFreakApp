package com.fitnessfreak.nutritionplans;

public class NutritionPlanModel {

    private String title;

    private String image;

    private String breakfast;

    private String snack1;

    private String lunch;

    private String snack2;

    private String dinner;

    private String snack3;

    private String dailyTotal;

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

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getSnack1() {
        return snack1;
    }

    public void setSnack1(String snack1) {
        this.snack1 = snack1;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getSnack2() {
        return snack2;
    }

    public void setSnack2(String snack2) {
        this.snack2 = snack2;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getSnack3() {
        return snack3;
    }

    public void setSnack3(String snack3) {
        this.snack3 = snack3;
    }

    public String getDailyTotal() {
        return dailyTotal;
    }

    public void setDailyTotal(String dailyTotal) {
        this.dailyTotal = dailyTotal;
    }

    public NutritionPlanModel(String title, String image, String breakfast, String snack1,
                              String lunch, String snack2, String dinner, String snack3,
                              String dailyTotal) {
        super();
        this.title = title;
        this.image = image;
        this.breakfast = breakfast;
        this.snack1 = snack1;
        this.lunch = lunch;
        this.snack2 = snack2;
        this.dinner = dinner;
        this.snack3 = snack3;
        this.dailyTotal = dailyTotal;
    }

    public NutritionPlanModel() {
        super();
    }
}
