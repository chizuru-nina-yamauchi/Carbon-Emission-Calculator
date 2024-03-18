package model;

public class Activity {
    private int activityId;
    private String activityName;
    private String activityDescription;

    // Default constructor
    public Activity() {
    }

    // Constructor with all fields
    public Activity(int activityId, String activityName, String activityDescription) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.activityDescription = activityDescription;
    }

    // Constructor without activityId
    public Activity(String activityName, String activityDescription) {
        this.activityName = activityName;
        this.activityDescription = activityDescription;
    }

    // Getters and Setters


    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    // toString method


    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", activityDescription='" + activityDescription + '\'' +
                '}';
    }
}
