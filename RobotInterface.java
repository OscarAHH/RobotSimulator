package RobotSimulator3;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 * Program to simulate an arena with multiple robots.
 */
public class RobotInterface { 

    private Scanner s; // Scanner to handle user input
    private RobotArena myArena; // Current robot arena
    private FileHandler fileHandler; // Handles file operations

    public RobotInterface() { 
        s = new Scanner(System.in); // Initialize the scanner
        myArena = new RobotArena(40, 10); // Create an arena with width 40 and height 10
        fileHandler = new FileHandler(myArena); // Link the file handler to the arena
        char ch = ' '; // Variable to store user choice

        do {
            System.out.print("Enter (A)dd Robot, get (I)nformation,"
                    + "\n(D)isplay all robots, (M)ove all robots,"
                    + "\n(S)ave arena, (L)oad arena, or e(X)it > "); 
            // Prompt user to choose an option

            ch = s.next().charAt(0); // Read the first character of user input
            s.nextLine(); // Clear the remaining input

            switch (ch) {
                case 'A': 
                case 'a': 
                    // Add a robot to the arena
                    myArena.addRobot(); 
                    break;

                case 'I': 
                case 'i': 
                    // Show arena information
                    System.out.println(myArena); 
                    break;

                case 'D': 
                case 'd': 
                    // Simulate and display all robots once
                    simulate(1); 
                    break;

                case 'M': 
                case 'm': 
                    // Simulate and move robots 10 times
                    simulate(10); 
                    break;

                case 'S': 
                case 's': 
                    // Save the current arena to a file
                    System.out.println("Enter the name of the file you would like to save > ");
                    String saveFilename = s.nextLine(); // Get the filename from user input
                    fileHandler.saveArena(saveFilename); // Save the arena
                    break;

                case 'L': 
                case 'l': 
                    // Load an arena from a file
                    System.out.println("Enter the name of the file you would like to load > ");
                    String loadFilename = s.nextLine(); // Get the filename from user input
                    RobotArena loadedArena = fileHandler.loadArena(loadFilename); // Load the arena
                    if (loadedArena != null) { 
                        myArena = loadedArena; // Update the current arena
                        fileHandler = new FileHandler(myArena); // Update the file handler
                        System.out.println("Arena loaded successfully!"); 
                        myArena.showRobots(); // Display robots in the loaded arena
                    }
                    break;

                case 'x': 
                    // Exit the program
                    ch = 'X'; 
                    break;
            }
        } while (ch != 'X'); // Keep looping until the user exits
        s.close(); // To close the scanner
    }


    private void simulate(int nTimes) {
        // Simulate robot movements and updates for nTimes cycles
        for (int ct = 0; ct < nTimes; ct++) {
            myArena.moveAllRobots(); // Move all robots
            System.out.println(); // Add a blank line for better readability
            myArena.showRobots(); // Display the arena with updated robot positions
            try {
                TimeUnit.MILLISECONDS.sleep(200); // Pause for 200 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace(); // Handle interruption during sleep
            }
        }
    }


    public static void main(String[] args) { 
        // Launches the RobotInterface
        new RobotInterface(); 
    }
}
