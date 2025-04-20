package RobotSimulator3;

import java.util.Random;

public enum Direction {

    N, E, S, W; // Four directions: N (North), E (East), S (South), W (West)

    public Direction next() {
        int currentIndex = this.ordinal(); // Get the current direction's index
        Direction currentDirection = values()[(currentIndex + 1) % values().length]; // Get the next direction, looping back to the start if necessary
        return currentDirection; // Return the next direction
    }

    public Direction previous() {
        int currentIndex = this.ordinal(); // Get the current direction's index
        Direction currentDirection = values()[(currentIndex + values().length - 1) % values().length]; // Get the previous direction, looping to the end if necessary
        return currentDirection; // Return the previous direction
    }

    public Direction getDirection() {
        int currentIndex = this.ordinal(); // Get the current direction's index
        Direction currentDirection = values()[currentIndex]; // Get the current direction
        return currentDirection; // Return the current direction
    }

    public static Direction randomDirection() {
        Random random = new Random(); // Create a random generator
        Direction[] directions = Direction.values(); // Get all directions
        return directions[random.nextInt(directions.length)]; // Return a random direction
    }

}
