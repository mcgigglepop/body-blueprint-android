package models;

public class User {
    private int id;
    private int age;
    private int gender;
    private int heightFeet;
    private int heightInches;
    private int currentWeight;
    private int currentWeightType;
    private int lifestyle;
    private int targetWeight;
    private int targetWeightType;


    public int getId() {
        // returns the user id
        return id;
    }

    public void setId(int id) {
        // sets the user id
        this.id = id;
    }

    public int getAge() {
        // returns the user age
        return age;
    }
    public void setAge(int age) {
        // sets the user age
        this.age = age;
    }

    public int getGender() {
        // returns the user gender
        return gender;
    }
    public void setGender(int gender) {
        // sets the user gender
        this.gender = gender;
    }

    public int getCurrentWeight() {
        // returns the user current weight
        return currentWeight;
    }
    public void setCurrentWeight(int currentWeight) {
        // sets the user current weight
        this.currentWeight = currentWeight;
    }

    public int getCurrentWeightType() {
        // returns the user current weight type
        return currentWeightType;
    }
    public void setCurrentWeightType(int currentWeightType) {
        // sets the user current weight type
        this.currentWeightType = currentWeightType;
    }

    public int getHeightFeet() {
        // returns the user height feet
        return heightFeet;
    }
    public void setHeightFeet(int heightFeet) {
        // sets the user height feet
        this.heightFeet = heightFeet;
    }
    public int getHeightInches() {
        // returns the user height inches
        return heightInches;
    }
    public void setHeightInches(int heightInches) {
        // sets the user height inches
        this.heightInches = heightInches;
    }

    public int getTargetWeight() {
        // returns the user target weight
        return targetWeight;
    }
    public void setTargetWeight(int targetWeight) {
        // sets the user target weight
        this.targetWeight = targetWeight;
    }

    public int getTargetWeightType() {
        // returns the user target weight type
        return targetWeightType;
    }
    public void setTargetWeightType(int targetWeightType) {
        // sets the user target weight type
        this.targetWeightType = targetWeightType;
    }

    public int getLifestyle() {
        // returns the user lifestyle
        return lifestyle;
    }
    public void setLifestyle(int lifestyle) {
        // sets the user lifestyle
        this.lifestyle = lifestyle;
    }
}
