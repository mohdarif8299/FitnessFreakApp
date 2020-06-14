package com.fitnessfreak.recieps;

public class ReciepeModel {
    private String title;
    private String image_url;
    private String instructions;
    private String ingredients;
    private String calories;
    private String cooking_time;
    private String food_type;
    private String video;
    private String benefits;
    private String food_category;
    private String description;

    public ReciepeModel(String title, String image_url, String instruction, String ingredients, String calories, String cooking_time, String food_type, String video,String food_category,String description,String benefits) {
        this.title = title;
        this.image_url = image_url;
        this.instructions = instruction;
        this.ingredients = ingredients;
        this.calories = calories;
        this.cooking_time = cooking_time;
        this.food_type = food_type;
        this.video = video;
        this.food_category = food_category;
        this.description = description;
        this.benefits = benefits;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFood_category() {
        return food_category;
    }

    public void setFood_category(String food_category) {
        this.food_category = food_category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getInstruction() {
        return instructions;
    }

    public void setInstruction(String instruction) {
        this.instructions = instruction;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(String cooking_time) {
        this.cooking_time = cooking_time;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public ReciepeModel() {
    }
}
