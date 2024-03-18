### Data Insertion into the tables
```sql

-- inserting the data

INSERT INTO Users(username, email, password_hash) VALUES
('Giyu Tomioka', 'giyu@hashira_training.com', SHA256('hash_giyu')),
('Shinobu Kocho', 'shinobu@hashira_training.com', SHA256('hash_shinobu')),
('Tengen Uzui', 'tengen@hashira_training.com', SHA256('hash_tengen')),
('Mitsuri Kanroji', 'mitsuri@hashira_training.com', SHA256('hash_mitsuri')),
('Muichiro Tokito', 'muichiro@hashira_training.com', SHA256('hash_muichiro')),
('Sanemi Shinazugawa', 'sanemi@hashira_training.com', SHA256('hash_sanemi')),
('Gyomei Himejima', 'gyomei@hashira_training.com', SHA256('hash_gyomei'));

INSERT INTO Activities (name, description) VALUES
('Cycling', 'Riding a bicycle'),
('Public Transport', 'Using buses, trains, etc.'),
('Electric Car', 'Driving an electric car'),
('Walking', 'Walking or hiking'),
('Car', 'Driving a conventional car'),
('Recycling', 'Recycling waste materials'),
('Solar Panels', 'Using solar panels for energy');

INSERT INTO Emission_Factors (activity_id, factor, unit) VALUES
(1, 0.015, 'kg CO2/km'),
(2, 0.03, 'kg CO2/km'),
(3, 0.02, 'kg CO2/km'),
(4, 0, 'kg CO2/km'),
(5, 0.12, 'kg CO2/km'),
(6, 0.05, 'kg CO2/kg'),
(7, 0.2, 'kg CO2/kWh');


INSERT INTO User_Emissions (user_id, activity_id, quantity, emission, date) VALUES
(1, 1, 50, 0.75, '2023-01-01'),
(1, 4, 30, 0, '2023-02-01'),
(2, 2, 20, 0.6, '2023-03-01'),
(2, 5, 40, 4.8, '2023-04-01'),
(3, 3, 10, 0.2, '2023-05-01'),
(3, 6, 5, 0.25, '2023-06-01'),
(4, 1, 40, 0.6, '2023-07-01'),
(4, 5, 60, 7.2, '2023-08-01'),
(5, 2, 15, 0.45, '2023-09-01'),
(5, 6, 20, 1, '2023-10-01'),
(6, 3, 30, 0.6, '2023-11-01'),
(6, 7, 25, 5, '2023-12-01'),
(7, 1, 10, 0.15, '2023-01-28'),
(7, 4, 50, 0, '2023-02-01');

INSERT INTO Emission_Goals (user_id, target_emission, start_date, end_date, status) VALUES
(1, 10, '2023-01-01', '2023-12-31', 'ONGOING'),
(2, 15, '2023-01-01', '2023-06-30', 'ACHIEVED'),
(3, 8, '2023-01-01', '2023-12-31', 'ONGOING'),
(4, 20, '2023-01-01', '2023-12-31', 'ONGOING'),
(5, 25, '2023-01-01', '2023-12-31', 'ONGOING'),
(6, 12, '2023-01-01', '2023-12-31', 'ONGOING'),
(7, 5, '2023-01-01', '2023-04-30', 'ACHIEVED');