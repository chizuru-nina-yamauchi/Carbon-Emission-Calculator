package model;

public class EmissionFactor {
    private int emissionFactorId;
    private int emissionFactorActivityId;
    private double emissionFactor;
    private String emissionFactorUnit;

    // Default constructor
    public EmissionFactor() {
    }

    // Constructor with all fields
    public EmissionFactor(int emissionFactorId, int emissionFactorActivityId, double emissionFactor, String emissionFactorUnit) {
        this.emissionFactorId = emissionFactorId;
        this.emissionFactorActivityId = emissionFactorActivityId;
        this.emissionFactor = emissionFactor;
        this.emissionFactorUnit = emissionFactorUnit;
    }

    // Constructor without emissionFactorId
    public EmissionFactor(int emissionFactorActivityId, double emissionFactor, String emissionFactorUnit) {
        this.emissionFactorActivityId = emissionFactorActivityId;
        this.emissionFactor = emissionFactor;
        this.emissionFactorUnit = emissionFactorUnit;
    }

    // Getters and Setters


    public int getEmissionFactorId() {
        return emissionFactorId;
    }

    public void setEmissionFactorId(int emissionFactorId) {
        this.emissionFactorId = emissionFactorId;
    }

    public int getEmissionFactorActivityId() {
        return emissionFactorActivityId;
    }

    public void setEmissionFactorActivityId(int emissionFactorActivityId) {
        this.emissionFactorActivityId = emissionFactorActivityId;
    }

    public double getEmissionFactor() {
        return emissionFactor;
    }

    public void setEmissionFactor(double emissionFactor) {
        this.emissionFactor = emissionFactor;
    }

    public String getEmissionFactorUnit() {
        return emissionFactorUnit;
    }

    public void setEmissionFactorUnit(String emissionFactorUnit) {
        this.emissionFactorUnit = emissionFactorUnit;
    }

    // toString method

    @Override
    public String toString() {
        return "EmissionFactor{" +
                "emissionFactorId=" + emissionFactorId +
                ", emissionFactorActivityId=" + emissionFactorActivityId +
                ", emissionFactor=" + emissionFactor +
                ", emissionFactorUnit='" + emissionFactorUnit + '\'' +
                '}';
    }
}
