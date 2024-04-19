package models;

public class User {
    private int id;
    private int age;
    private String gender;
    private int currentWeight;
    private String currentWeightType;
    private int heightFeet;
    private int heightInches;
    private String lifestyle;
    private int targetWeight;
    private String targetWeightType;
    private int bmr;
    private int dab;


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

    public String getGender() {
        // returns the user gender
        return gender;
    }
    public void setGender(String gender) {
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

    public String getCurrentWeightType() {
        // returns the user current weight type
        return currentWeightType;
    }
    public void setCurrentWeightType(String currentWeightType) {
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
    public String getLifestyle() {
        // returns the user lifestyle
        return lifestyle;
    }
    public void setLifestyle(String lifestyle) {
        // sets the user lifestyle
        this.lifestyle = lifestyle;
    }
    public int getTargetWeight() {
        // returns the user target weight
        return targetWeight;
    }
    public void setTargetWeight(int targetWeight) {
        // sets the user target weight
        this.targetWeight = targetWeight;
    }

    public String getTargetWeightType() {
        // returns the user target weight type
        return targetWeightType;
    }
    public void setTargetWeightType(String targetWeightType) {
        // sets the user target weight type
        this.targetWeightType = targetWeightType;
    }

    public int getBMR() {
        // returns the users bmr
        return bmr;
    }
    public void setBMR(int bmr) {
        // sets the users bmr
        this.bmr = bmr;
    }

    public int getDAB() {
        // returns the users dab
        return dab;
    }
    public void setDAB(int dab) {
        // sets the users dab
        this.dab = dab;
    }
}
