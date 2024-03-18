### Create sql tables

```sql

CREATE TABLE Users(
user_id SERIAL PRIMARY KEY,
username VARCHAR(255) NOT NULL ,
email VARCHAR(255) NOT NULL,
password_hash VARCHAR(255) NOT NULL
);


CREATE TABLE Activities(
activity_id SERIAL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
description TEXT NOT NULL
);


CREATE TABLE Emission_Factors(
factor_id SERIAL PRIMARY KEY,
activity_id INT REFERENCES Activities(activity_id) NOT NULL,
factor DECIMAL NOT NULL,
unit VARCHAR(50) NOT NULL
);


CREATE TABLE User_Emissions(
emission_id SERIAL PRIMARY KEY,
user_id INT REFERENCES Users(user_id) NOT NULL ,
activity_id INT REFERENCES Activities(activity_id) NOT NULL ,
quantity DECIMAL NOT NULL,
emission DECIMAL NOT NULL,
date DATE NOT NULL
);


CREATE TABLE Emission_Goals(
goal_id SERIAL PRIMARY KEY,
user_id INT REFERENCES Users(user_id) NOT NULL,
target_emission DECIMAL NOT NULL,
start_date DATE NOT NULL,
end_date DATE NOT NULL,
status VARCHAR(50) NOT NULL
);