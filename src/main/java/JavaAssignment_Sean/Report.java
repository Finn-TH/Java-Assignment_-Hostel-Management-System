package JavaAssignment_Sean;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Report {
    private String reportName;

    public Report(String reportName) {
        this.reportName = reportName;
    }

    public void generateRoomAvailReport() {
        String fileName = "room.txt";
        int availableRoomsCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] roomInfo = line.split(",");
                if (roomInfo.length >= 4) {
                    String availability = roomInfo[3];
                    if (availability.equalsIgnoreCase("available")) {
                        availableRoomsCount++;
                    }
                }
            }

            System.out.println("Report: " + reportName);
            System.out.println("Number of available rooms: " + availableRoomsCount);
        } catch (IOException e) {
            System.out.println("Error reading room information from file: " + fileName);
        }
    }
}

