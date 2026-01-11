package entity;

public class BloodStock {
    private String blood_group;
    private int total_units;

    public BloodStock() {
    }

    public BloodStock(String blood_group, int total_units) {
        this.blood_group = blood_group;
        this.total_units = total_units;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public int getTotal_units() {
        return total_units;
    }

    public void setTotal_units(int total_units) {
        this.total_units = total_units;
    }
}
