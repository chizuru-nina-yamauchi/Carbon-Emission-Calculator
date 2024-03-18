package util;
/*
 * Enum class to store all the CRUD(Create, Read, Update, Delete) queries for all the entities
 *
 * */
public enum CRUDQueries {
    // Enum values for all the entities

    // CRUD queries for User entity
    CREATE_USER("INSERT INTO Users (username, email, password_hash) VALUES (?, ?, ?)"),
    READ_USER_BY_ID("SELECT * FROM Users WHERE user_id = ?"),
    READ_ALL_USERS("SELECT * FROM Users"),
    UPDATE_USER("UPDATE Users SET username = ?, email = ?, password_hash = ? WHERE user_id = ?"),
    DELETE_USER("DELETE FROM Users WHERE user_id = ?"),

    // CRUD queries for Activity entity

    CREATE_ACTIVITY("INSERT INTO Activities (name, description) VALUES (?, ?)"),
    READ_ACTIVITY_BY_ID("SELECT * FROM Activities WHERE activity_id = ?"),
    READ_ALL_ACTIVITIES("SELECT * FROM Activities"),
    UPDATE_ACTIVITY("UPDATE Activities SET name = ?, description = ? WHERE activity_id = ?"),
    DELETE_ACTIVITY("DELETE FROM Activities WHERE activity_id = ?"),

    // CRUD queries for Emission_Factor entity

    CREATE_EMISSION_FACTOR("INSERT INTO Emission_Factors (activity_id, factor, unit) VALUES (?, ?, ?)"),
    READ_EMISSION_FACTOR_BY_ID("SELECT * FROM Emission_Factors WHERE factor_id = ?"),
    READ_ALL_EMISSION_FACTORS("SELECT * FROM Emission_Factors"),
    UPDATE_EMISSION_FACTOR("UPDATE Emission_Factors SET activity_id = ?, factor = ?, unit = ? WHERE factor_id = ?"),
    DELETE_EMISSION_FACTOR("DELETE FROM Emission_Factors WHERE factor_id = ?"),

    // CRUD queries for User_Emission entity

    CREATE_USER_EMISSION("INSERT INTO User_Emissions (user_id, activity_id, quantity, emission, date) VALUES (?, ?, ?, ?, ?)"),
    READ_USER_EMISSION_BY_ID("SELECT * FROM User_Emissions WHERE emission_id = ?"),
    READ_ALL_USER_EMISSIONS("SELECT * FROM User_Emissions"),
    UPDATE_USER_EMISSION("UPDATE User_Emissions SET user_id = ?, activity_id = ?, quantity = ?, emission = ?, date = ? WHERE emission_id = ?"),
    DELETE_USER_EMISSION("DELETE FROM User_Emissions WHERE emission_id = ?"),

    // CRUD queries for Emission_Goal entity

    CREATE_EMISSION_GOAL("INSERT INTO Emission_Goals (user_id, target_emission, start_date, end_date, status) VALUES (?, ?, ?, ?, ?)"),
    READ_EMISSION_GOAL_BY_ID("SELECT * FROM Emission_Goals WHERE goal_id = ?"),
    READ_ALL_EMISSION_GOALS("SELECT * FROM Emission_Goals"),
    UPDATE_EMISSION_GOAL("UPDATE Emission_Goals SET user_id = ?, target_emission = ?, start_date = ?, end_date = ?, status = ? WHERE goal_id = ?"),
    DELETE_EMISSION_GOAL("DELETE FROM Emission_Goals WHERE goal_id = ?");

    // Attribute to store the query
    private final String query;


    // Constructor to initialize the query
    CRUDQueries(String query) {
        this.query = query;
    }

    // Getter method to get the query
    public String getQuery() {
        return query;
    }
}
