package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Database {

    public static void phoneEvents() {
        // SQLite database connection parameters
        String url = "jdbc:sqlite::memory:";  // In-memory database
        String tableName = "phone_events";

        // CSV file path and name
        String csvFilePath = "/home/lulibo/IdeaProjects/Database/src/main/java/Prospective/Cell C Event detail.csv";

        try (Connection connection = DriverManager.getConnection(url);
             BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            // Create table
            createTable(connection, tableName);

            // Insert data from CSV
            insertDataFromCSV(connection, tableName, reader);

            System.out.println("SQLite database created and data imported successfully.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection, String tableName) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE " + tableName + " (" +
                        "Phone TEXT, " +
                        "Event_type TEXT, " +
                        "Date TEXT, " +
                        "Time_Stamp TEXT)")) {
            statement.execute();
        }
    }

    private static void insertDataFromCSV(Connection connection, String tableName, BufferedReader reader)
            throws SQLException, IOException {
        String line;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO " + tableName + " (Phone, Event_type, Date, Time_Stamp) VALUES (?, ?, ?, ?)")) {

            // Skip the CSV header
            reader.readLine();

            // Read and insert data from the CSV
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                statement.setString(1, data[0].trim());
                statement.setString(2, data[1].trim());
                statement.setString(3, data[2].trim());
                statement.setString(4, data[3].trim());

                statement.executeUpdate();
            }
        }
    }

    public static void emailAdresses() {
        // SQLite database connection parameters
        String url = "jdbc:sqlite::memory:";  // In-memory database
        String tableName = "email_adresses";

        // CSV file path and name
        String csvFilePath = "/home/lulibo/IdeaProjects/Database/src/main/java/Prospective/Employee File.csv";

        try (Connection connection = DriverManager.getConnection(url);
             BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {

            // Create table
            createTableForEmployees(connection, tableName);

            // Insert data from CSV
            insertdatafromcsv(connection, tableName, reader);

            // Update email addresses in the table
            updateEmailAddresses(connection, tableName);

            System.out.println("SQLite database created, data imported, and email addresses updated successfully.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void createTableForEmployees(Connection connection, String tableName) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE " + tableName + " (" +
                        "employee_guid TEXT, " +
                        "first_name TEXT, " +
                        "last_name TEXT, " +
                        "gender TEXT, " +
                        "email TEXT, " +
                        "age TEXT, " +
                        "birthday TEXT, " +
                        "active TEXT, " +
                        "street TEXT, " +
                        "postal TEXT, " +
                        "province TEXT, " +
                        "city TEXT, " +
                        "longitude TEXT, " +
                        "latitude TEXT, " +
                        "package TEXT, " +
                        "vendor TEXT, " +
                        "phone TEXT, " +
                        "package_cost TEXT, " +
                        "contract_start TEXT, " +
                        "contract_end TEXT)")) {
            statement.execute();
        }
    }


    private static void insertdatafromcsv(Connection connection, String tableName, BufferedReader reader)
            throws SQLException, IOException {
        String line;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO " + tableName +
                        "(employee_guid, first_name, last_name, gender, email, age, birthday, active, street, postal, province, city, longitude, latitude, package, vendor, phone, package_cost, contract_start, contract_end) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            // Skip the CSV header
            reader.readLine();

            // Read and insert data from the CSV
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                statement.setString(1, data[0].trim());
                statement.setString(2, data[1].trim());
                statement.setString(3, data[2].trim());
                statement.setString(4, data[3].trim());
                statement.setString(5, data[4].trim());
                statement.setString(6, data[5].trim());
                statement.setString(7, data[6].trim());
                statement.setString(8, data[7].trim());
                statement.setString(9, data[8].trim());
                statement.setString(10, data[9].trim());
                statement.setString(11, data[10].trim());
                statement.setString(12, data[11].trim());
                statement.setString(13, data[12].trim());
                statement.setString(14, data[13].trim());
                statement.setString(15, data[14].trim());
                statement.setString(16, data[15].trim());
                statement.setString(17, data[16].trim());
                statement.setString(18, data[17].trim());
                statement.setString(19, data[18].trim());
                statement.setString(20, data[19].trim());

                statement.executeUpdate();
            }
        }
    }


    private static void updateEmailAddresses(Connection connection, String tableName) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE " + tableName + " SET email = REPLACE(email, '@', '@company.')")) {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public static void main(String[] args) {
        emailAdresses();
        phoneEvents();
    } }
