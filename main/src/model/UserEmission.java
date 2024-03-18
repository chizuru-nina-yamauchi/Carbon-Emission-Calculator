package model;

import java.time.LocalDate;

public class UserEmission {
    private int emissionId;
    private int userId;
    private int activityId;
    private double quantity;
    private double emission;
    private LocalDate date;

    // Default constructor
    public UserEmission() {
    }

    // Constructor with all fields
    public UserEmission(int emissionId, int userId, int activityId, double quantity, double emission, LocalDate date) {
        this.emissionId = emissionId;
        this.userId = userId;
        this.activityId = activityId;
        this.quantity = quantity;
        this.emission = emission;
        this.date = date;
    }

    // Constructor without emissionId
    public UserEmission(int userId, int activityId, double quantity, double emission, LocalDate date) {
        this.userId = userId;
        this.activityId = activityId;
        this.quantity = quantity;
        this.emission = emission;
        this.date = date;
    }

    // Getters and Setters


    public int getEmissionId() {
        return emissionId;
    }

    public void setEmissionId(int emissionId) {
        this.emissionId = emissionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getEmission() {
        return emission;
    }

    public void setEmission(double emission) {
        this.emission = emission;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // toString method

    @Override
    public String toString() {
        return "UserEmission{" +
                "emissionId=" + emissionId +
                ", userId=" + userId +
                ", activityId=" + activityId +
                ", quantity=" + quantity +
                ", emission=" + emission +
                ", date=" + date +
                '}';
    }
}
