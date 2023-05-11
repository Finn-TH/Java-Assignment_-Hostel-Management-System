package JavaAssignment_Sean;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;






public class Admin {
    // instance variables
    private String username;
    private String password;

    // constructor
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
        // initialize roomList, studentRecords, and reports as needed
    }

    //Admin Login Method
    public boolean login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String usernameInput = scanner.nextLine();

        System.out.print("Enter password: ");
        String passwordInput = scanner.nextLine();

        String fileName = "admin_login.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] adminInfo = line.split(",");
                String username = adminInfo[0];
                String password = adminInfo[1];
                if (username.equals(usernameInput) && password.equals(passwordInput)) {
                    System.out.println("Successful login!");
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading admin information from file: " + fileName);
        }

        System.out.println("Wrong username or password. Login failed.");
        return false;
    }


    // Add a room to the room list
    public void addRoomInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();

        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();

        scanner.nextLine(); // Consume newline character

        System.out.print("Enter room type: ");
        String type = scanner.nextLine();

        System.out.print("Enter availability (true/false): ");
        boolean available = scanner.nextBoolean();

        System.out.print("Enter room price: ");
        int price = scanner.nextInt();

        Room room = new Room(roomNumber, capacity, type, available, price);
        room.writeRoomToFile();

        System.out.println("Room information added successfully.");
    }


    // View the room information stored in the file
    public void viewRoomInfo() {
        String fileName = "room.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] roomInfo = line.split(",");
                if (roomInfo.length == 5) {
                    int roomNumber = Integer.parseInt(roomInfo[0]);
                    int capacity = Integer.parseInt(roomInfo[1]);
                    String type = roomInfo[2];
                    String availabilityString = roomInfo[3];
                    boolean available = availabilityString.equalsIgnoreCase("available");
                    int price = Integer.parseInt(roomInfo[4]);

                    // Display the room information
                    System.out.println("Room Number: " + roomNumber);
                    System.out.println("Capacity: " + capacity);
                    System.out.println("Type: " + type);
                    System.out.println("Availability: " + (available ? "Available" : "Unavailable"));
                    System.out.println("Price: " + price);
                    System.out.println("--------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading room information from file: " + fileName);
        }
    }

    //Update room info method
    public void updateRoomInfo() {
        viewRoomInfo(); // Show current room information to the admin
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the room number you wish to update: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Find the room in the file and update its information
        String fileName = "room.txt";
        List<String> fileContent = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean roomFound = false;
            while ((line = reader.readLine()) != null) {
                String[] roomInfo = line.split(",");
                int currentRoomNumber = Integer.parseInt(roomInfo[0]);
                if (currentRoomNumber == roomNumber) {
                    // Update the room information
                    System.out.print("Enter new capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character

                    System.out.print("Enter new room type: ");
                    String type = scanner.nextLine();

                    System.out.print("Is the room available (true/false)? ");
                    boolean available = scanner.nextBoolean();

                    System.out.print("Enter room price: ");
                    int price = scanner.nextInt();

                    String updatedRoomInfo = currentRoomNumber + "," + capacity + "," + type + "," + (available ? "available" : "unavailable") + "," + price;
                    fileContent.add(updatedRoomInfo);
                    roomFound = true;
                    System.out.println("Room information updated successfully.");
                } else {
                    fileContent.add(line);
                }
            }
            if (!roomFound) {
                System.out.println("Room not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading room information from file: " + fileName);
        }

        // Write the updated room information back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : fileContent) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing room information to file: " + fileName);
        }
    }

    //Delete Room info Method

    public void deleteRoomInfo() {
        viewRoomInfo(); // Show the current rooms to the admin
        System.out.print("Enter the room number to delete: ");
        Scanner scanner = new Scanner(System.in);
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        String fileName = "room.txt";
        String tempFileName = "room_temp.txt";
        File inputFile = new File(fileName);
        File tempFile = new File(tempFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] roomInfo = line.split(",");
                int existingRoomNumber = Integer.parseInt(roomInfo[0]);
                if (existingRoomNumber != roomNumber) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            // Close the resources
            reader.close();
            writer.close();

            // Delete the original file
            inputFile.delete();

            // Rename the temporary file to the original file name
            tempFile.renameTo(inputFile);

            System.out.println("Room " + roomNumber + " has been deleted.");
        } catch (IOException e) {
            System.out.println("Error deleting room " + roomNumber + ".");
        }
    }

    // Generate Number of Rooms Report
    public void reportRoomAvail() {
        Report roomReport = new Report("Room Availability Report");
        roomReport.generateRoomAvailReport();
    }




}
