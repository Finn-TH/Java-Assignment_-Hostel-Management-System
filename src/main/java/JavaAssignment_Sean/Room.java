package JavaAssignment_Sean;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Room {
    // instance variables
    private int roomNumber;
    private int capacity;
    private String type;
    private boolean available;
    private int price;

    // constructor
    public Room(int roomNumber, int capacity, String type, boolean available, int price) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.type = type;
        this.available = available;
        this.price = price;
    }


    // write room information to a text file
    public void writeRoomToFile() {
        String fileName = "room.txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            String availability = available ? "available" : "unavailable";
            String roomInfo = roomNumber + "," + capacity + "," + type + "," + availability + "," + price + "\n";
            writer.write(roomInfo);
            System.out.println("Room information written to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing room information to file: " + fileName);
        }
    }



}
