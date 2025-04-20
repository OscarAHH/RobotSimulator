package RobotSimulator3;

public class Robot {

	
	// Initialize new coordinates as the current position of the robot.
    // xPos() and yPos() methods are assumed to return the current x and y coordinates of the robot.
    private int xPos, yPos; // x and y coordinates of the robot
    private String ID; // Identifier for the robot
    private Direction direction; // Current direction the robot is facing

    Robot(int xPos, int yPos, String ID, Direction direction) {
        this.xPos = xPos; // Set x coordinate
        this.yPos = yPos; // Set y coordinate
        this.ID = ID; // Set robot ID
        this.direction = direction; // Set initial direction
    }

    public int xPos() {
        return xPos; // Get the robot's x coordinate
    }

    public int yPos() {
        return yPos; // Get the robot's y coordinate
    }

    public String ID() {
        return ID; // Get the robot's ID
    }

    public Direction direction() {
        return direction; // Get the robot's current direction
    }

    public boolean isHere(int x, int y) {
        return x == xPos() && y == yPos(); // Check if the robot is at the given coordinates
    }

    public void tryToMove(RobotArena arena) {
        int newX = xPos(), newY = yPos(); // Initialize new coordinates as current coordinates

     // Determine the movement direction and calculate new coordinates accordingly.
        // The robot's direction is obtained through the direction.getDirection() method.
        switch (direction.getDirection()) { // Determine movement based on direction
            case N: newY--; break; // Move up (decrease y)
            case W: newX--; break; // Move left (decrease x)
            case S: newY++; break; // Move down (increase y)
            case E: newX++; break; // Move right (increase x)
        }

        if (arena.canMoveHere(newX, newY)) { // Check if the robot can move to the new position
            xPos = newX; // Update x coordinate
            yPos = newY; // Update y coordinate
        } else {
            direction = direction.next(); // Change direction if movement is blocked
        }
    }

    public void displayRobot(ConsoleCanvas canvas) {
        if (xPos() >= 0 && xPos() < canvas.array[0].length && yPos() >= 0 && yPos() < canvas.array.length) {
            // Check if the robot is within the canvas boundaries
            System.out.print("Displaying robot: "); // Notify that the robot is being displayed
            System.out.println(toString()); // Print robot details
            canvas.array[yPos][xPos] = ID().charAt(1); // Display robot on the canvas using the second character of its ID
        } else {
            System.out.printf("\nRobot is out of bounds: %s", toString()); // Notify that the robot is out of bounds
        }
    }

    public String toString() {
        return "Robot ID: " + ID() + ", coordinates are: (" + xPos() + "," + yPos() + "), direction is: " + direction() + ".\n";
        // Return robot's ID, position, and direction as a string
    }
}
