package services;

import config.DBConnection;
import entity.BloodStock;
import entity.Donor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BloodBankDAO {

    public boolean addDonor(Donor donor) throws SQLException {
        String insertQuery = "INSERT INTO donor (name, age, gender, blood_group, contact_number, city, donated_units) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String stockUpdate = "UPDATE blood_stock SET total_units = total_units + ? WHERE blood_group = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertQuery)) {

            ps.setString(1, donor.getName());
            ps.setInt(2, donor.getAge());
            ps.setString(3, donor.getGender());
            ps.setString(4, donor.getBlood_group());
            ps.setString(5, donor.getContact_number());
            ps.setString(6, donor.getCity());
            ps.setInt(7, donor.getDonated_units());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // Update blood stock
                try (PreparedStatement pstmt = connection.prepareStatement(stockUpdate)) {
                    pstmt.setInt(1, donor.getDonated_units());
                    pstmt.setString(2, donor.getBlood_group());
                    pstmt.executeUpdate();
                }
                return true;
            }
            return false;
        }
    }

    public List<Donor> getAllDonors() throws SQLException {
        String query = "SELECT * FROM donor";
        List<Donor> donors = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Donor donor = new Donor();
                donor.setDonor_id(rs.getInt("donor_id"));
                donor.setName(rs.getString("name"));
                donor.setAge(rs.getInt("age"));
                donor.setGender(rs.getString("gender"));
                donor.setBlood_group(rs.getString("blood_group"));
                donor.setContact_number(rs.getString("contact_number"));
                donor.setCity(rs.getString("city"));
                donor.setDonated_units(rs.getInt("donated_units"));
                donor.setLast_donation_date(rs.getTimestamp("last_donation_date"));
                donors.add(donor);
            }
        }
        return donors;
    }

    public List<Donor> searchDonorsByBloodGroup(String blood_group) throws SQLException {
        String query = "SELECT * FROM donor WHERE blood_group = ?";
        List<Donor> donors = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, blood_group);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Donor donor = new Donor();
                    donor.setDonor_id(rs.getInt("donor_id"));
                    donor.setName(rs.getString("name"));
                    donor.setAge(rs.getInt("age"));
                    donor.setGender(rs.getString("gender"));
                    donor.setBlood_group(rs.getString("blood_group"));
                    donor.setContact_number(rs.getString("contact_number"));
                    donor.setCity(rs.getString("city"));
                    donor.setDonated_units(rs.getInt("donated_units"));
                    donors.add(donor);
                }
            }
        }
        return donors;
    }

    public boolean updateDonor(int donor_id, String contact_number, String city) throws SQLException {
        String query = "UPDATE donor SET contact_number = ?, city = ? WHERE donor_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, contact_number);
            ps.setString(2, city);
            ps.setInt(3, donor_id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteDonor(int donor_id) throws SQLException {
        String query = "DELETE FROM donor WHERE donor_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, donor_id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<BloodStock> getAllBloodStocks() throws SQLException {
        String query = "SELECT * FROM blood_stock WHERE total_units != 0";
        List<BloodStock> stocks = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                BloodStock stock = new BloodStock(
                        rs.getString("blood_group"),
                        rs.getInt("total_units")
                );
                stocks.add(stock);
            }
        }
        return stocks;
    }

    public boolean addUnits(BloodStock bloodStock) throws SQLException {
        String query = "UPDATE blood_stock SET total_units = total_units + ? WHERE blood_group = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, bloodStock.getTotal_units());
            ps.setString(2, bloodStock.getBlood_group());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean removeUnits(String blood_group, int units) throws SQLException {
        String checkQuery = "SELECT total_units FROM blood_stock WHERE blood_group = ?";
        String updateQuery = "UPDATE blood_stock SET total_units = GREATEST(total_units - ?, 0) WHERE blood_group = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement checkPs = connection.prepareStatement(checkQuery)) {

            checkPs.setString(1, blood_group);

            try (ResultSet rs = checkPs.executeQuery()) {
                if (rs.next()) {
                    int totalUnits = rs.getInt("total_units");

                    // Check if sufficient units available
                    if (totalUnits >= units && totalUnits > 0) {
                        try (PreparedStatement updatePs = connection.prepareStatement(updateQuery)) {
                            updatePs.setInt(1, units);
                            updatePs.setString(2, blood_group);
                            return updatePs.executeUpdate() > 0;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int getAvailableUnits(String blood_group) throws SQLException {
        String query = "SELECT total_units FROM blood_stock WHERE blood_group = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, blood_group);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total_units");
                }
            }
        }
        return -1;
    }
}