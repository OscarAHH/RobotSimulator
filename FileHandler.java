package RobotSimulator3;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    private RobotArena myArena; // Instance of RobotArena to manage robots

    public FileHandler(RobotArena arena) {
        this.myArena = arena; // Assign the provided arena
    }

    public void createNewArena() {
        myArena = new RobotArena(40, 10); // Create a new arena with fixed dimensions
        System.out.println("New arena created - everything has been reset!"); // Notify about arena reset
    }

    
    public void saveArena(String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            // Open file in append mode
            List<Robot> robots = myArena.getRobots(); // Get all robots in the arena
            writer.write("\nArena size is: " + myArena.xSize() + " by " + myArena.ySize() + ".\n"); // Save arena size
            if (robots.isEmpty()) {
                writer.write("No robots in the arena!\n"); // Notify if there are no robots
            } else {
                for (Robot robot : robots) {
                    // Save robot details: ID, position, and direction
                    writer.write("Details of the robot: ID " + robot.ID() + ", with coordinates (" + robot.xPos() + "," + robot.yPos() + "), with direction facing " + robot.direction() + ".\n");
                }
            }
            System.out.println("Writing to file was successful!"); // Confirm successful save
        } catch (IOException e) {
            System.out.println("Writing to file was unsuccessful!"); // Notify about save failure
            e.printStackTrace(); // Print error details
        }
    }

    
    public RobotArena loadArena(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename)); // Read file content
            boolean arenaSizeSet = false; // Flag to track if arena size is set

            if (!lines.isEmpty()) { // Check if file has content
                for (int i = 0; i < lines.size(); i++) { // Loop through file lines
                    String line = lines.get(i).trim(); // Remove extra spaces from the line
                    if (line.startsWith("Arena size is:") && !arenaSizeSet) {
                        // Check if line contains arena size
                        String[] parts = line.split(" "); // Split the line into parts
                        if (parts.length >= 6) { // Validate the format
                            try {
                                int xSize = Integer.parseInt(parts[3]); // Extract arena width
                                int ySize = Integer.parseInt(parts[5].replace(".", "")); // Extract arena height
                                myArena = new RobotArena(xSize, ySize); // Create arena with specified dimensions
                                arenaSizeSet = true; // Mark arena size as set
                            } catch (NumberFormatException e) {
                                System.out.println("Error parsing arena size at line " + (i + 1) + ". Skipping...");
                                continue; // Skip invalid lines
                            }
                        } else {
                            System.out.println("Invalid format for arena size at line " + (i + 1) + ". Skipping...");
                            continue;
                        }
                    } else if (line.startsWith("Details of the robot:")) {
                        
                    	
                    	// Check if line contains robot details
                        try {
                            String[] robotParts = line.split("[ ,()]+"); // Split line into parts
                            System.out.println("Parsed parts: " + Arrays.toString(robotParts)); // Debugging: print parts
                            if (robotParts.length >= 14) { // Validate the format
                                String id = robotParts[5]; // Extract robot ID
                                int xPos = Integer.parseInt(robotParts[8]); // Extract x-coordinate
                                int yPos = Integer.parseInt(robotParts[9]); // Extract y-coordinate
                                Direction direction = Direction.valueOf(robotParts[13].replace(".", "")); // Extract direction
                                Robot robot = new Robot(xPos, yPos, id, direction); // Create robot instance
                                myArena.getRobots().add(robot); // Add robot to arena
                            } else {
                                System.out.println("Invalid format for robot details at line " + (i + 1) + ": " + line);
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error parsing robot details at line " + (i + 1) + ": " + line);
                            e.printStackTrace(); // Print error details
                            continue;
                        }
                    }
                }
                if (arenaSizeSet) {
                    System.out.println("Reading from file was successful!"); // Confirm successful load
                    return myArena; // Return the loaded arena
                } else {
                    System.out.println("Arena size information is missing or malformed in the file."); // Notify about missing arena size
                }
            }
        } catch (IOException e) {
            System.out.println("Reading from file was unsuccessful!"); // Notify about load failure
            e.printStackTrace(); // Print error details
        }
        return null; // Return null if loading fails
    }
}
