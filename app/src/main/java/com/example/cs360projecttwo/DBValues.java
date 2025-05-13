package com.example.cs360projecttwo;

import java.util.Date;

public class DBValues {
    // Variables for each value of a database
    private String dailyWeight;
    private String goalWeight;
    private String dateAdded;
    private String accountName;
    private String accountPassword;
    private int id;

    // Default constructor
    public DBValues(String dailyWeight, String goalWeight, String dateAdded, String accountName,
                    String accountPassword) {
        this.dailyWeight = dailyWeight;
        this.goalWeight = goalWeight;
        this.dateAdded = dateAdded;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
    }

    // Setters and Getters for each attribute
    public void SetDailyWeight(String dailyWeight) {
        this.dailyWeight = dailyWeight;
    }

    public String GetDailyWeight() {
        return dailyWeight;
    }

    public void SetGoalWeight(String goalWeight) {
        this.goalWeight = goalWeight;
    }
    public String GetGoalWeight() {
        return goalWeight;
    }

    public void SetDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
    public String GetDateAdded() {
        return dateAdded;
    }

    public void SetAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String GetAccountName() {
        return  accountName;
    }
    public void SetAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
    public String GetAccountPassword() {
        return accountPassword;
    }

    public void SetId (int id) {
        this.id = id;
    }

    public int GetId() {
        return id;
    }

}
