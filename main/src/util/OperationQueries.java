package util;

/**
 * Enum class representing additional SQL operation queries for comparing emissions and other tasks.
 */
public enum OperationQueries {
    /**
     * SQL query to calculate total emissions for a user.
     */
    CALCULATE_TOTAL_EMISSIONS_FOR_USER(
            "SELECT SUM(emission) AS total_emission " +
                    "FROM user_emissions " +
                    "WHERE user_id = ?"
    ),

    /**
     * SQL query to compare emissions between different activities.
     */
    COMPARE_EMISSIONS_BETWEEN_ACTIVITIES(
            "SELECT a.name AS activity_name, AVG(ue.emission) AS average_emission " +
                    "FROM Activities a " +
                    "JOIN User_Emissions ue ON a.activity_id = ue.activity_id " +
                    "GROUP BY a.name " +
                    "ORDER BY average_emission DESC"
    ),

    /**
     * SQL query to list activities with emissions above a certain threshold.
     */
    LIST_ACTIVITIES_ABOVE_THRESHOLD(
            "SELECT a.name AS activity_name, ue.emission " +
                    "FROM Activities a " +
                    "JOIN User_Emissions ue ON a.activity_id = ue.activity_id " +
                    "WHERE ue.emission > ?"
    ),

    /**
     * SQL query to calculate monthly emissions for a user.
     */
    CALCULATE_MONTHLY_EMISSIONS_FOR_USER(
            "SELECT EXTRACT(MONTH FROM ue.date) AS month, SUM(ue.emission) AS monthly_emission " +
                    "FROM User_Emissions ue " +
                    "WHERE ue.user_id = ? " +
                    "GROUP BY month"
    ),

    /**
     * SQL query to find users who have exceeded their emission goals.
     */
    FIND_USERS_EXCEEDED_EMISSION_GOALS(
            "SELECT u.user_id, u.username " +
                    "FROM Users u " +
                    "JOIN ( " +
                    "   SELECT ue.user_id, SUM(ue.emission) AS total_emission " +
                    "   FROM User_Emissions ue " +
                    "   GROUP BY ue.user_id " +
                    ") AS user_total_emission ON u.user_id = user_total_emission.user_id " +
                    "JOIN Emission_Goals eg ON u.user_id = eg.user_id " +
                    "WHERE user_total_emission.total_emission > eg.target_emission"
    ),

    /**
     * SQL query to filter results based on a custom enum type.
     */
    FIND_EMISSION_GOALS_WITH_STATUS(
            "SELECT * FROM Emission_Goals WHERE status = ?"
    ),

    /**
     * SQL query to aggregate emissions by activity type and filter by minimum emission.
     */
    AGGREGATE_EMISSIONS_BY_ACTIVITY_AND_FILTER_BY_MINIMUM_EMISSION(
            "SELECT a.name AS activity_name, SUM(ue.emission) AS total_emission " +
                    "FROM Activities a " +
                    "JOIN User_Emissions ue ON a.activity_id = ue.activity_id " +
                    "GROUP BY a.name " +
                    "HAVING SUM(ue.emission) > ?"
    ),

    /**
     * SQL query to identify top 3 activities with the highest average emissions.
     */
    IDENTIFY_TOP_3_ACTIVITIES_WITH_HIGHEST_AVERAGE_EMISSIONS(
            "SELECT a.name AS activity_name, AVG(ue.emission) AS average_emission " +
                    "FROM Activities a " +
                    "JOIN User_Emissions ue ON a.activity_id = ue.activity_id " +
                    "GROUP BY a.name " +
                    "ORDER BY AVG(ue.emission) DESC " +
                    "LIMIT 3"
    );

    private final String query;

    OperationQueries(String query) {
        this.query = query;
    }

    // Getter method to get the query
    public String getQuery() {
        return query;
    }
}
