package com.seveneleven;

import java.sql.*;
import java.util.ArrayList;

public class DBAdaptor {

    private static Connection db_conn;

    public static void initialise() throws ClassNotFoundException, SQLException
    {

        Class.forName("com.mysql.jdbc.Driver");
        db_conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "mdl941027");

    }

    public static boolean authenticate(String username, String password) {
        try {
            String query = "SELECT password FROM users WHERE username ='" + username + "'";
            Statement stmt = db_conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            if (password.equals(rs.getString("password"))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static ArrayList<String[]> getCivilians() {
        try {
            String query = "SELECT * FROM civilians";
            Statement stmt = db_conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String[]> table = new ArrayList<String[]>();
            while (rs.next()) {
                table.add(new String[]{rs.getString("NRIC"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("address")});
            }
            return table;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<String[]> getHazards() {
        try {
            String query = "SELECT * FROM hazards";
            Statement stmt = db_conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String[]> table = new ArrayList<String[]>();
            while (rs.next()) {
                table.add(new String[]{rs.getString("id"), rs.getString("description"), rs.getString("location"), rs.getString("datetime"), rs.getString("status")});
            }
            return table;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<String[]> getIncidents() {
        try {
            String query = "SELECT * FROM incidents";
            Statement stmt = db_conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String[]> table = new ArrayList<String[]>();
            while (rs.next()) {
                table.add(new String[]{rs.getString("id"), rs.getString("description"), rs.getString("location"), rs.getString("datetime"), rs.getString("status")});
            }
            return table;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<String[]> getMedicalFacilities() {
        try {
            String query = "SELECT * FROM medical_facilities";
            Statement stmt = db_conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String[]> table = new ArrayList<String[]>();
            while (rs.next()) {
                table.add(new String[]{rs.getString("id"), rs.getString("location"), rs.getString("description"), rs.getString("active")});
            }
            return table;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<String[]> getSafetyShelters() {
        try {
            String query = "SELECT * FROM safety_shelters";
            Statement stmt = db_conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String[]> table = new ArrayList<String[]>();
            while (rs.next()) {
                table.add(new String[]{rs.getString("id"), rs.getString("location"), rs.getString("description"), rs.getString("active")});
            }
            return table;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<String[]> getSituationReports() {
        try {
            String query = "SELECT * FROM situation_reports";
            Statement stmt = db_conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String[]> table = new ArrayList<String[]>();
            while (rs.next()) {
                table.add(new String[]{rs.getString("id"), rs.getString("description"), rs.getString("datetime")});
            }
            return table;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<String[]> getSMSAlerts() {
        try {
            String query = "SELECT * FROM sms_alerts";
            Statement stmt = db_conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String[]> table = new ArrayList<String[]>();
            while (rs.next()) {
                table.add(new String[]{rs.getString("id"), rs.getString("description"), rs.getString("datetime")});
            }
            return table;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<String[]> getSocialMediaAlerts() {
        try {
            String query = "SELECT * FROM socialmedia_alerts";
            Statement stmt = db_conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String[]> table = new ArrayList<String[]>();
            while (rs.next()) {
                table.add(new String[]{rs.getString("id"), rs.getString("description"), rs.getString("datetime")});
            }
            return table;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static ArrayList<String[]> getUsers() {
        try {
            String query = "SELECT * FROM users";
            Statement stmt = db_conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String[]> table = new ArrayList<String[]>();
            while (rs.next()) {
                table.add(new String[]{rs.getString("username"), rs.getString("password"), rs.getString("department"), rs.getString("email")});
            }
            return table;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static boolean addCivilian(String NRIC, String name, String phone, String email, String address) {
        try {
            String insert = "'" + NRIC + "','" + name + "','" + phone + "','" + email + "','" + address + "')";
            String query = "INSERT INTO civilians (NRIC, name, phone, email, address) VALUES (" + insert;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean addHazard(String description, String location, String status) {
        try {
            String insert = "'" + description + "','" + location + "','" + status + "')";
            String query = "INSERT INTO hazards (description, location, status) VALUES (" + insert;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean addIncident(String description, String location, String status) {
        try {
            String insert = "'" + description + "','" + location + "','" + status + "')";
            String query = "INSERT INTO incidents (description, location, status) VALUES (" + insert;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean addMedicalFacility(String location, String description, String active) {
        try {
            String insert = "'" + location + "','" + description + "','" + active + "')";
            String query = "INSERT INTO medical_facilities (location, description, active) VALUES (" + insert;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean addSafetyShelter(String location, String description, String active) {
        try {
            String insert = "'" + location + "','" + description + "','" + active + "')";
            String query = "INSERT INTO safety_shelters (location, description, active) VALUES (" + insert;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean addSituationReport(String description) {
        try {
            String insert = "'" + description + "')";
            String query = "INSERT INTO situation_reports (description) VALUES (" + insert;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean addSMSAlert(String description) {
        try {
            String insert = "'" + description + "')";
            String query = "INSERT INTO sms_alerts (description) VALUES (" + insert;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean addSocialMediaAlert(String description, String platform) {
        try {
            String insert = "'" + description + "','" + platform + "')";
            String query = "INSERT INTO socialmedia_alerts (description, platform) VALUES (" + insert;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean addUser(String username, String password, String department, String email) {
        try {
            String insert = "'" + username + "','" + password + "','" + department + "','" + email + "')";
            String query = "INSERT INTO users (username, password, department, email) VALUES (" + insert;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean updateCivilian(String NRIC, String name, String phone, String email, String address) {
        try {
            String set = "name = '" + name + "', phone = '" + phone + "', email = '" + email + "', address = '" + address + "'";
            String condition = "NRIC = '" + NRIC + "'";
            String query = "UPDATE civilians SET " + set + " WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean updateHazard(String id, String description, String location, String status) {
        try {
            String set = "description = '" + description + "', location = '" + location + "', datetime = NOW(), status = '" + status + "'";
            String condition = "id = '" + id + "'";
            String query = "UPDATE hazards SET " + set + " WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean updateIncident(String id, String description, String location, String status) {
        try {
            String set = "description = '" + description + "', location = '" + location + "', datetime = NOW(), status = '" + status + "'";
            String condition = "id = '" + id + "'";
            String query = "UPDATE incidents SET " + set + " WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean updateMedicalFacility(String id, String location, String description, String active) {
        try {
            String set = "location = '" + location + "', description = '" + description + "', active = '" + active + "'";
            String condition = "id = '" + id + "'";
            String query = "UPDATE medical_facilities SET " + set + " WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean updateSafetyShelter(String id, String location, String description, String active) {
        try {
            String set = "location = '" + location + "', description = '" + description + "', active = '" + active + "'";
            String condition = "id = '" + id + "'";
            String query = "UPDATE safety_shelters SET " + set + " WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean updateSituationReport(String id, String description) {
        try {
            String set = "description = '" + description + "', datetime = NOW()";
            String condition = "id = '" + id + "'";
            String query = "UPDATE situation_reports SET " + set + " WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean updateSMSAlert(String id, String description) {
        try {
            String set = "description = '" + description + "', datetime = NOW()";
            String condition = "id = '" + id + "'";
            String query = "UPDATE sms_alerts SET " + set + " WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean updateSocialMediaAlert(String id, String description, String platform) {
        try {
            String set = "description = '" + description + "', platform = '" + platform + "', datetime = NOW()";
            String condition = "id = '" + id + "'";
            String query = "UPDATE socialmedia_alerts SET " + set + " WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean updateUsers(String id, String username, String password, String department, String email) {
        try {
            String set = "username = '" + username + "', password = '" + password + "', department = '" + department + "', email = '" + email + "'";
            String condition = "id = '" + id + "'";
            String query = "UPDATE users SET " + set + " WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteCivilian(String NRIC) {
        try {
            String condition = "NRIC = '" + NRIC + "'";
            String query = "DELETE FROM civilians WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteHazard(String id) {
        try {
            String condition = "id = '" + id + "'";
            String query = "DELETE FROM hazards WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteIncident(String id) {
        try {
            String condition = "id = '" + id + "'";
            String query = "DELETE FROM incidents WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteMedicalFacility(String id) {
        try {
            String condition = "id = '" + id + "'";
            String query = "DELETE FROM medical_facilities WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteSafetyShelter(String id) {
        try {
            String condition = "id = '" + id + "'";
            String query = "DELETE FROM safety_shelters WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteSituationReport(String id) {
        try {
            String condition = "id = '" + id + "'";
            String query = "DELETE FROM situation_reports WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteSMSAlert(String id) {
        try {
            String condition = "id = '" + id + "'";
            String query = "DELETE FROM sms_alerts WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteSocialMediaAlert(String id) {
        try {
            String condition = "id = '" + id + "'";
            String query = "DELETE FROM socialmedia_alerts WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteUsers(String username) {
        try {
            String condition = "username = '" + username + "'";
            String query = "DELETE FROM users WHERE " + condition;
            Statement stmt = db_conn.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
