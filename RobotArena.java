package RobotSimulator3;
import java.util.ArrayList;
import java.util.Random;

public class RobotArena {

    private int xSize, ySize; // Dimensions of the arena
    private ArrayList<Robot> robots; // List of robots in the arena
    private int robotCounter; // Number of robots currently in the arena

    RobotArena(int xSize, int ySize) {
        this.xSize = xSize; // Set arena width
        this.ySize = ySize; // Set arena height
        this.robots = new ArrayList<>(); // Initialize robot list
        this.robotCounter = 0; // Start with no robots
    }

    public int xSize() {
        return xSize; // Get arena width
    }

    public int ySize() {
        return ySize; // Get arena height
    }

    public int robotCounter() {
        return robotCounter; // Get the number of robots
    }

    public ArrayList<Robot> getRobots() {
        return robots; // Get the list of robots
    }

    public boolean canMoveHere(int x, int y) {
        if (x <= 0 || y <= 0 || y >= ySize - 1 || x >= xSize - 1) { // Check if position is out of bounds
            return false; // Return false if position is invalid
        }
        for (Robot robot : robots) { // Check each robot in the arena
            if (robot.isHere(x, y)) { // Check if a robot is already at the position
                return false; // Return false if position is occupied
            }
        }
        return true; // Return true if position is valid and unoccupied
    }

    public void addRobot() {
        if (robotCounter >= 10) { // Check if max robot limit is reached
            System.out.println("Max amount of 10 robots has been achieved!"); // Notify max limit
        } else {
            Random rand = new Random(); // Create a random generator
            int xPos = rand.nextInt(xSize() - 2) + 1; // Generate a random x position within bounds
            int yPos = rand.nextInt(ySize() - 2) + 1; // Generate a random y position within bounds
            String ID = "R" + (robotCounter()); // Create a robot ID
            Direction direction = Direction.randomDirection(); // Assign a random direction
            Robot newRobot = new Robot(xPos, yPos, ID, direction); // Create a new robot
            robots.add(newRobot); // Add the robot to the list
            robotCounter++; // Increment the robot counter
            System.out.println(toString()); // Print arena details
        }
    }

    public void moveAllRobots() {
        ConsoleCanvas canvas = new ConsoleCanvas(xSize(), ySize()); // Create a canvas with the arena dimensions
        for (Robot robot : robots) { // Loop through all robots
            if (robot != null) { // Check if robot is not null
                robot.tryToMove(this); // Attempt to move the robot
            }
        }
        canvas.showIt(); // Display the canvas after all robots move
    }

    public void showRobots() {
        ConsoleCanvas canvas = new ConsoleCanvas(xSize(), ySize()); // Create a canvas with the arena dimensions
        for (Robot robot : robots) { // Loop through all robots
            if (robot != null) { // Check if robot is not null
                robot.displayRobot(canvas); // Display the robot on the canvas
            }
        }
        canvas.showIt(); // Show the canvas with all robots displayed
    }

    public String toString() {
        return "The arena size is " + xSize + " by " + ySize + " with " + robotCounter + " robots."; // Return arena details as a string
    }
}
