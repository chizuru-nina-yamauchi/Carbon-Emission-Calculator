package model;

import java.time.LocalDate;

public class EmissionGoal {
    private int emissionGoalId;
    private int userId;
    private double targetEmission;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;


    // Default constructor
    public EmissionGoal() {
    }

    // Constructor with all fields
    public EmissionGoal(int emissionGoalId, int userId, double targetEmission, LocalDate startDate, LocalDate endDate, String status) {
        this.emissionGoalId = emissionGoalId;
        this.userId = userId;
        this.targetEmission = targetEmission;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Constructor without emissionGoalId
    public EmissionGoal(int userId, double targetEmission, LocalDate startDate, LocalDate endDate, String status) {
        this.userId = userId;
        this.targetEmission = targetEmission;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getters and Setters

    public int getEmissionGoalId() {
        return emissionGoalId;
    }

    public void setEmissionGoalId(int emissionGoalId) {
        this.emissionGoalId = emissionGoalId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTargetEmission() {
        return targetEmission;
    }

    public void setTargetEmission(double targetEmission) {
        this.targetEmission = targetEmission;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method

    @Override
    public String toString() {
        return "EmissionGoal{" +
                "emissionGoalId=" + emissionGoalId +
                ", userId=" + userId +
                ", targetEmission=" + targetEmission +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}
