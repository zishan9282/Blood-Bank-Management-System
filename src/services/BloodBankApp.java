package services;

import entity.BloodStock;
import entity.Donor;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BloodBankApp {

    private static final BloodBankDAO dao = new BloodBankDAO();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                try {
                    displayMenu();
                    int choice = sc.nextInt();
                    System.out.println();

                    switch (choice) {
                        case 1:
                            addDonorMenu(sc);
                            break;
                        case 2:
                            viewDonorsMenu();
                            break;
                        case 3:
                            searchDonorMenu(sc);
                            break;
                        case 4:
                            updateDonorMenu(sc);
                            break;
                        case 5:
                            deleteDonorMenu(sc);
                            break;
                        case 6:
                            addUnitsMenu(sc);
                            break;
                        case 7:
                            viewBloodStockMenu();
                            break;
                        case 8:
                            removeUnitsMenu(sc);
                            break;
                        case 0:
                            exitApplication();
                            return;
                        default:
                            System.out.println("Invalid choice! Please try again.");
                    }
                } catch (SQLException e) {
                    System.out.println("SQL Exception Occurred: " + e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    sc.nextLine(); // Clear invalid input
                } catch (InterruptedException e) {
                    System.out.println("Operation Interrupted: " + e.getMessage());
                }
            }
        } finally {
            System.out.println("Thank you for using Blood Bank System....");
        }
    }

    private static void displayMenu() {
        System.out.println("========== Welcome to Blood Bank ==========");
        System.out.println("1. Add Donor");
        System.out.println("2. View Donors");
        System.out.println("3. Search Donor By Blood Group");
        System.out.println("4. Update Donor Info");
        System.out.println("5. Delete Donor");
        System.out.println("6. Add Units(Manually)");
        System.out.println("7. View Blood Stock");
        System.out.println("8. Remove Units(Request)");
        System.out.println("0. Exit");
        System.out.print("Enter your Choice: ");
    }

    private static void addDonorMenu(Scanner sc) throws SQLException {
        System.out.print("Enter Your Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter your Age: ");
        int age = sc.nextInt();
        System.out.print("Enter your Gender: ");
        String gender = sc.next();
        System.out.print("Enter your Blood Group: ");
        String bg = sc.next();
        System.out.print("Enter your Contact NO.: ");
        String contact = sc.next();
        System.out.print("Enter your City: ");
        String city = sc.next();
        System.out.print("Enter the Donated Units: ");
        int units = sc.nextInt();

        Donor donor = new Donor(name, age, gender, bg, contact, city, units);
        boolean success = dao.addDonor(donor);

        if (success) {
            System.out.println("Row(s) Affected.");
            System.out.println("Donor Inserted Successfully.");
            System.out.println("Blood Group (" + bg.toUpperCase() + ") inserted into the stock successfully.");
        } else {
            System.out.println("No row(s) Affected.");
        }
        System.out.println();
    }

    private static void viewDonorsMenu() throws SQLException {
        List<Donor> donors = dao.getAllDonors();

        System.out.println("========== Available Donors ==========");
        if (donors.isEmpty()) {
            System.out.println("No, Donor Available at this moment.");
        } else {
            for (Donor donor : donors) {
                System.out.println("Donor Id: " + donor.getDonor_id());
                System.out.println("Name: " + donor.getName());
                System.out.println("Age: " + donor.getAge());
                System.out.println("Gender: " + donor.getGender());
                System.out.println("Blood Group: " + donor.getBlood_group().toUpperCase());
                System.out.println("Contact No. :" + donor.getContact_number());
                System.out.println("City: " + donor.getCity());
                System.out.println("Donated Units: " + donor.getDonated_units());
                System.out.println("Last Donation Date: " + donor.getLast_donation_date());
                System.out.println("-----------------------------");
            }
        }
        System.out.println("======================================");
        System.out.println();
    }

    private static void searchDonorMenu(Scanner sc) throws SQLException {
        System.out.print("Enter the Blood Group: ");
        String blood = sc.next();

        List<Donor> donors = dao.searchDonorsByBloodGroup(blood);

        if (donors.isEmpty()) {
            System.out.println("No Donor found for this blood group.");
        } else {
            for (Donor donor : donors) {
                System.out.println("Donor Id: " + donor.getDonor_id());
                System.out.println("Name: " + donor.getName());
                System.out.println("Age: " + donor.getAge());
                System.out.println("Gender: " + donor.getGender());
                System.out.println("Contact No.: " + donor.getContact_number());
                System.out.println("City: " + donor.getCity());
                System.out.println("------------------------------");
            }
        }
        System.out.println();
    }

    private static void updateDonorMenu(Scanner sc) throws SQLException {
        System.out.print("Enter the Donor Id: ");
        int id = sc.nextInt();
        System.out.print("Enter the Contact No.: ");
        String conNo = sc.next();
        System.out.print("Enter the City: ");
        String cty = sc.next();

        boolean success = dao.updateDonor(id, conNo, cty);

        if (success) {
            System.out.println("1 row(s) Affected.");
            System.out.println("Donor information updated successfully.");
        } else {
            System.out.println("No row(s) Affected.");
        }
        System.out.println();
    }

    private static void deleteDonorMenu(Scanner sc) throws SQLException {
        System.out.print("Enter the Donor Id: ");
        int donorId = sc.nextInt();

        boolean success = dao.deleteDonor(donorId);

        if (success) {
            System.out.println("1 row(s) Affected.");
            System.out.println("Donor Record Deleted.");
        } else {
            System.out.println("No row(s) affected.");
        }
        System.out.println();
    }

    private static void addUnitsMenu(Scanner sc) throws SQLException {
        System.out.print("Enter Blood Group: ");
        String bloodGroup = sc.next();
        System.out.print("Enter the Units: ");
        int units = sc.nextInt();

        BloodStock bloodStock = new BloodStock(bloodGroup, units);
        boolean success = dao.addUnits(bloodStock);

        if (success) {
            System.out.println("1 row(s) Affected.");
            System.out.println("Added " + units + " units of Blood Group (" + bloodGroup.toUpperCase() + ") to stock.");
        } else {
            System.out.println("No row(s) Affected.");
            System.out.println("Or Invalid blood type inserted.");
        }
        System.out.println();
    }

    private static void viewBloodStockMenu() throws SQLException {
        List<BloodStock> stocks = dao.getAllBloodStocks();

        System.out.println("========== Available Stock ==========");
        if (stocks.isEmpty()) {
            System.out.println("No stock is available.");
        } else {
            for (BloodStock stock : stocks) {
                System.out.println("Blood Group: " + stock.getBlood_group().toUpperCase());
                System.out.println("Total Units: " + stock.getTotal_units());
                System.out.println("-------------------");
            }
        }
        System.out.println();
    }

    private static void removeUnitsMenu(Scanner sc) throws SQLException {
        System.out.print("Enter the Blood Group: ");
        String bloodGroup = sc.next();
        System.out.print("Enter the Units to Remove: ");
        int units = sc.nextInt();

        // Check available units first
        int availableUnits = dao.getAvailableUnits(bloodGroup);

        if (availableUnits == -1) {
            System.out.println("Blood Group (" + bloodGroup.toUpperCase() + ") not found.");
            System.out.println("Invalid blood group is inserted.");
        } else if (availableUnits == 0) {
            System.out.println("Can't remove from the stock because Blood Group (" + bloodGroup.toUpperCase() + ") total unit is: 0");
        } else if (availableUnits < units) {
            System.out.println("Can't remove from the stock because Blood Group (" + bloodGroup.toUpperCase() + ") total unit is: " + availableUnits);
        } else {
            boolean success = dao.removeUnits(bloodGroup, units);
            if (success) {
                System.out.println("1 row(s) Affected.");
                System.out.println("Removed " + units + " units of Blood Group (" + bloodGroup.toUpperCase() + ") from stock.");
            } else {
                System.out.println("No row(s) affected.");
            }
        }
        System.out.println();
    }

    private static void exitApplication() throws InterruptedException {
        System.out.print("Exiting");
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            Thread.sleep(500);
        }
        System.out.println();
    }
}