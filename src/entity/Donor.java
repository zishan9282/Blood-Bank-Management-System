package entity;

import java.sql.Timestamp;

public class Donor {
    private int donor_id;
    private String name;
    private int age;
    private String gender;
    private String blood_group;
    private String contact_number;
    private String city;
    private int donated_units;
    private Timestamp last_donation_date;

    // Default constructor
    public Donor() {
    }

    // Constructor without ID (for new donors)
    public Donor(String name, int age, String gender, String blood_group,
                 String contact_number, String city, int donated_units) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.blood_group = blood_group;
        this.contact_number = contact_number;
        this.city = city;
        this.donated_units = donated_units;
    }

    // Getters
    public int getDonor_id() {
        return donor_id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getCity() {
        return city;
    }

    public int getDonated_units() {
        return donated_units;
    }

    public Timestamp getLast_donation_date() {
        return last_donation_date;
    }

    // Setters
    public void setDonor_id(int donor_id) {
        this.donor_id = donor_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDonated_units(int donated_units) {
        this.donated_units = donated_units;
    }

    public void setLast_donation_date(Timestamp last_donation_date) {
        this.last_donation_date = last_donation_date;
    }
}