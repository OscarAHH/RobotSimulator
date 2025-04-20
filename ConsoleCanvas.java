package RobotSimulator3;

public class ConsoleCanvas {

    private int xSize, ySize; // Canvas dimensions (width and height)
    char[][] array; // 2D array to represent the canvas
    private String studentID = "32014653"; // Student ID

    ConsoleCanvas(int xSize, int ySize) {

        this.xSize = xSize; // Set width
        this.ySize = ySize; // Set height
        this.array = new char[ySize][xSize]; // Create a 2D array for the canvas

        // Fill the canvas with spaces
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                array[i][j] = ' '; // Fill each cell with a space
            }
        }

        // Add borders to the canvas
        for (int j = 0; j < xSize; j++) {
            array[0][j] = '#'; // Top border
            array[ySize - 1][j] = '#'; // Bottom border
        }
        for (int i = 0; i < ySize; i++) {
            array[i][0] = '#'; // Left border
            array[i][xSize - 1] = '#'; // Right border
        }

        // Place the student ID in the center of the top border
        int idStartPos = (xSize - studentID.length()) / 2; // Calculate starting position
        for (int i = 0; i < studentID.length(); i++) {
            if (idStartPos + i >= 0 && idStartPos + i < xSize) { // Ensure placement is within bounds
                array[0][idStartPos + i] = studentID.charAt(i); // Add each character of the ID
            }
        }

        toString(); // Print canvas size information
    }

    public void showIt() { // Display the canvas
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                System.out.print(array[i][j]); // Print each character
            }
            System.out.println(); // Move to the next line
        }
    }

    public String toString() { // Return canvas size
        return "The size is: " + xSize + " by " + ySize + "."; // Width x Height
    }
}
